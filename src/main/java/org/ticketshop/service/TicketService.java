package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.dto.BoughtTicketDTO;
import org.ticketshop.dto.SendBuyInfoDTO;
import org.ticketshop.mapper.BoughtTicketMapper;
import org.ticketshop.mapper.SendBuyInfoMapper;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Manifestation;
import org.ticketshop.model.Ticket;
import org.ticketshop.repository.TicketRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final ManifestationService manifestationService;
    private final BoughtTicketMapper boughtTicketMapper;
    private final BigDecimal PRICE_FAN_PIT = BigDecimal.valueOf(2);
    private final BigDecimal PRICE_VIP = BigDecimal.valueOf(4);

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, UserService userService,
                         ManifestationService manifestationService, SendBuyInfoMapper sendBuyInfoMapper,
                         BoughtTicketMapper boughtTicketMapper) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.manifestationService = manifestationService;
        this.boughtTicketMapper = boughtTicketMapper;
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
        userService.getUser(buyerId);

        BigDecimal priceRegular = BigDecimal.valueOf(numRegular).multiply(manifestation.getPriceRegular());
        BigDecimal priceFan = BigDecimal.valueOf(numFan).multiply(manifestation.getPriceRegular().multiply(PRICE_FAN_PIT));
        BigDecimal priceVip = BigDecimal.valueOf(numVip).multiply(manifestation.getPriceRegular().multiply(PRICE_VIP));

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

        return boughtTicketMapper.toDto(numRegular, numFan, numVip, priceRegular.add(priceFan).add(priceVip));
    }

}
