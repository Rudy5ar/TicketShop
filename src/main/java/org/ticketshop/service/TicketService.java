package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.dto.ReservedTicketDTO;
import org.ticketshop.dto.SendReserveInfoDTO;
import org.ticketshop.dto.TicketDTO;
import org.ticketshop.mapper.ReservedTicketMapper;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Manifestation;
import org.ticketshop.model.Ticket;
import org.ticketshop.model.User;
import org.ticketshop.repository.TicketRepository;
import org.ticketshop.repository.UserRepository;

import java.math.BigDecimal;
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
    private final ReservedTicketMapper reservedTicketMapper;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserService userService,
                         ManifestationService manifestationService,
                         ReservedTicketMapper reservedTicketMapper, TicketMapper ticketMapper, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.manifestationService = manifestationService;
        this.reservedTicketMapper = reservedTicketMapper;
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

    @Transactional
    public ReservedTicketDTO reserveTickets(SendReserveInfoDTO sendReserveInfoDTO) {
        Manifestation manifestation = manifestationService.getManifestation(sendReserveInfoDTO.manifestationId());
        int numRegular = sendReserveInfoDTO.ticketsToReserve().get("regular");
        int numFan = sendReserveInfoDTO.ticketsToReserve().get("fan_pit");
        int numVip = sendReserveInfoDTO.ticketsToReserve().get("vip");

        if(manifestation.getNumOfRegularTickets() < numRegular
                || manifestation.getNumOfFanpitTickets() < numFan
                || manifestation.getNumOfVipTickets() < numVip){
            throw new RuntimeException("Not enough tickets to reserve");
        }

        User user = userService.getUser(sendReserveInfoDTO.buyerId());

        sendReserveInfoDTO.ticketsToReserve().forEach((type, num) -> ticketRepository.save(Ticket.builder()
                        .type(type)
                        .date(manifestation.getDate())
                        .price(getPrice(type, manifestation))
                        .status(1)
                        .manifestation(manifestation)
                        .user(user)
                        .build()));

        manifestation.setNumOfRegularTickets(manifestation.getNumOfRegularTickets() - numRegular);
        manifestation.setNumOfFanpitTickets(manifestation.getNumOfFanpitTickets() - numFan);
        manifestation.setNumOfVipTickets(manifestation.getNumOfVipTickets() - numVip);
        manifestationService.updateManifestation(manifestation, sendReserveInfoDTO.manifestationId());

        BigDecimal finalPrice = getPrice("regular", manifestation)
                .add(getPrice("fan_pit", manifestation))
                .add(getPrice("vip", manifestation));

        return reservedTicketMapper.toDto(sendReserveInfoDTO.ticketsToReserve(), finalPrice);
    }

    private BigDecimal getPrice(String type, Manifestation manifestation) {
        return switch (type) {
            case "regular" -> manifestation.getPriceRegular();
            case "fan_pit" -> manifestation.getPriceRegular().multiply(PRICE_FAN_PIT);
            case "vip" -> manifestation.getPriceRegular().multiply(PRICE_VIP);
            default -> BigDecimal.valueOf(0);
        };
    }

    @Transactional
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

    public void cancelTickets(Long userId, Long manifestationId) {
        ticketRepository.deleteAll(userService.getUser(userId).getTickets()
                .stream()
                .filter(ticket -> ticket.getStatus() == 1 && ticket.getManifestation().getId().equals(manifestationId))
                .toList());
    }
}
