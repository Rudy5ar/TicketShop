package org.ticketshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.ticketshop.dto.ReservedTicketDTO;
import org.ticketshop.dto.SendReserveInfoDTO;
import org.ticketshop.mapper.ReservedTicketMapper;
import org.ticketshop.model.Manifestation;
import org.ticketshop.model.Ticket;
import org.ticketshop.model.User;
import org.ticketshop.repository.ManifestationRepository;
import org.ticketshop.repository.TicketRepository;
import org.ticketshop.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private ManifestationRepository manifestationRepository;

    @Mock
    private ManifestationService manifestationService;

    @Mock
    private ReservedTicketMapper reservedTicketMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Test
    void testGetAllTickets() {
        Ticket ticket = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(100))
                .type("regular")
                .status(1)
                .build();
        Ticket ticket2 = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(50))
                .type("fan_pit")
                .status(2)
                .build();
        Ticket ticket3 = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(10))
                .type("vip")
                .status(1)
                .build();

        given(ticketRepository.findAll(PageRequest.of(0, 3))).willReturn(new PageImpl<>(List.of(ticket, ticket2, ticket3)));
        Page<Ticket> tickets = ticketService.getAllTickets(PageRequest.of(0, 3));

        assertThat(tickets).isNotNull();
        assertThat(tickets.getTotalElements()).isEqualTo(3);
    }

    @Test
    void testReserveTickets(){
        User user = new User(16L, "ime", "pass", "prvo", "drugo", 10, "buyer", 0, null, null);

        Manifestation manifestation = Manifestation.builder()
                .id(16L)
                .name("Concert")
                .type("regular")
                .numOfSeats(50)
                .date(LocalDateTime.now())
                .priceRegular(BigDecimal.valueOf(50))
                .status(String.valueOf("innactive"))
                .userSeller(user)
                .numOfRegularTickets(50)
                .numOfFanpitTickets(40)
                .numOfVipTickets(10)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(100))
                .type("regular")
                .status(1)
                .build();

        given(ticketRepository.save(any(Ticket.class))).willReturn(ticket);
        given(manifestationService.getManifestation(16L)).willReturn(manifestation);
        given(userService.getUser(16L)).willReturn(user);
        given(manifestationService.updateManifestation(manifestation, 16L)).willReturn(manifestation);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("regular", 3);
        map.put("fan_pit", 3);
        map.put("vip", 3);

        given(reservedTicketMapper.toDto(map, BigDecimal.valueOf(350))).willReturn(new ReservedTicketDTO(map, BigDecimal.valueOf(500)));

        ReservedTicketDTO reservedTickets = ticketService.reserveTickets(new SendReserveInfoDTO(map, 16L, 16L));

        assertThat(reservedTickets).isNotNull();
        assertThat(reservedTickets.reservedTickets().size()).isEqualTo(3);
    }

}
