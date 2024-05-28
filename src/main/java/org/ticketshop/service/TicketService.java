package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.dto.BoughtTicketDTO;
import org.ticketshop.dto.TicketDTO;
import org.ticketshop.mapper.BoughtTicketMapper;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Manifestation;
import org.ticketshop.model.Ticket;
import org.ticketshop.model.User;
import org.ticketshop.repository.TicketRepository;
import org.ticketshop.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final BigDecimal PRICE_FAN_PIT = BigDecimal.valueOf(2);
    private final BigDecimal PRICE_VIP = BigDecimal.valueOf(4);
    private final int REWARD_REGULAR = 50;
    private final int REWARD_FAN_PIT = 100;
    private final int REWARD_VIP = 200;


    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final ManifestationService manifestationService;
    private final BoughtTicketMapper boughtTicketMapper;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserService userService,
                         ManifestationService manifestationService,
                         BoughtTicketMapper boughtTicketMapper, TicketMapper ticketMapper, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.manifestationService = manifestationService;
        this.boughtTicketMapper = boughtTicketMapper;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<Ticket> getAllTickets(Pageable page) {
        return ticketRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    }

    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket updateTicket(Long id, Ticket ticket) {
        getTicketById(id);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public BoughtTicketDTO reserveTickets(int numRegular, int numFan, int numVip, Long manifestationId, Long buyerId) {
        Manifestation manifestation = manifestationService.getManifestation(manifestationId);
        if(manifestation.getNumOfRegularTickets() < numRegular || manifestation.getNumOfFanpitTickets() < numFan || manifestation.getNumOfVipTickets() < numVip){
            throw new RuntimeException("Not enough tickets to reserve");
        }
        userService.getUser(buyerId);

        BigDecimal priceRegular = BigDecimal.valueOf(numRegular).multiply(manifestation.getPriceRegular());
        BigDecimal priceFan = BigDecimal.valueOf(numFan).multiply(manifestation.getPriceRegular().multiply(PRICE_FAN_PIT));
        BigDecimal priceVip = BigDecimal.valueOf(numVip).multiply(manifestation.getPriceRegular().multiply(PRICE_VIP));

        createBoughtTickets(numRegular, numFan, numVip, buyerId, manifestation);

        manifestation.setNumOfRegularTickets(manifestation.getNumOfRegularTickets() - numRegular);
        manifestation.setNumOfFanpitTickets(manifestation.getNumOfFanpitTickets() - numFan);
        manifestation.setNumOfVipTickets(manifestation.getNumOfVipTickets() - numVip);
        manifestationService.updateManifestation(manifestation, manifestationId);

        return boughtTicketMapper.toDto(numRegular, numFan, numVip, priceRegular.add(priceFan).add(priceVip));
    }

    private void createBoughtTickets(int numRegular, int numFan, int numVip, Long buyerId, Manifestation manifestation) {
        for (int i = 0; i < numRegular; i++){
            ticketRepository.save(Ticket.builder()
                            .date(LocalDateTime.now())
                            .price(manifestation.getPriceRegular())
                            .status(1)
                            .type("regular")
                            .user(userService.getUser(buyerId))
                            .manifestation(manifestation)
                            .build());
        }

        for (int i = 0; i < numFan; i++){
            ticketRepository.save(Ticket.builder()
                    .date(LocalDateTime.now())
                    .price(manifestation.getPriceRegular().multiply(PRICE_FAN_PIT))
                    .status(1)
                    .type("fan_pit")
                    .user(userService.getUser(buyerId))
                    .manifestation(manifestation)
                    .build());
        }

        for (int i = 0; i < numVip; i++){
            ticketRepository.save(Ticket.builder()
                    .date(LocalDateTime.now())
                    .price(manifestation.getPriceRegular().multiply(PRICE_VIP))
                    .status(1)
                    .type("vip")
                    .user(userService.getUser(buyerId))
                    .manifestation(manifestation)
                    .build());
        }
    }

    public List<TicketDTO> buyReservedTickets(Long userId) {
        User user = userService.getUser(userId);
        List<Ticket> usersTickets = user.getTickets();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for(Ticket ticket : usersTickets){
            if(ticket.getStatus() == 1){
                ticket.setStatus(2);
                switch (ticket.getType()){
                    case "regular":
                        user.setRewardPoints(user.getRewardPoints() + REWARD_REGULAR);
                        break;
                    case "fan_pit":
                        user.setRewardPoints(user.getRewardPoints() + REWARD_FAN_PIT);
                        break;
                    case "vip":
                        user.setRewardPoints(user.getRewardPoints() + REWARD_VIP);
                        break;
                }
                ticketRepository.save(ticket);
                ticketDTOs.add(ticketMapper.toDto(ticket));
            }
        }
        userRepository.save(user);
        return ticketDTOs;
    }
}
