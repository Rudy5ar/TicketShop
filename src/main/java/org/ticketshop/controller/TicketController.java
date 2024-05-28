package org.ticketshop.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.dto.BoughtTicketDTO;
import org.ticketshop.dto.SendBuyInfoDTO;
import org.ticketshop.dto.TicketDTO;
import org.ticketshop.mapper.SendBuyInfoMapper;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Ticket;
import org.ticketshop.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final SendBuyInfoMapper sendBuyInfoMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper, SendBuyInfoMapper sendBuyInfoMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
        this.sendBuyInfoMapper = sendBuyInfoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ticketMapper.toDto(ticketService.getTicketById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getTicket(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return new ResponseEntity<>(ticketService.getAllTickets(PageRequest.of(pageNumber, pageSize))
                .stream().map(ticketMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            Ticket savedTicket = ticketService.saveTicket(ticketMapper.fromDto(ticketDTO));
            return new ResponseEntity<>(ticketMapper.toDto(savedTicket), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable long id, @RequestBody TicketDTO ticketDTO) {
        try {
            Ticket updatedTicket = ticketService.updateTicket(id, ticketMapper.fromDto(ticketDTO));
            return new ResponseEntity<>(ticketMapper.toDto(updatedTicket), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
    }

    @PostMapping("/buyTickets")
    public ResponseEntity<BoughtTicketDTO> reserveTickets(@RequestBody SendBuyInfoDTO sendBuyInfoDTO) {
        return new ResponseEntity<>(ticketService.reserveTickets(sendBuyInfoDTO.numRegular(), sendBuyInfoDTO.numFan(),
                                    sendBuyInfoDTO.numVip(), sendBuyInfoDTO.manifestationId(), sendBuyInfoDTO.buyerId()),
                                    HttpStatus.OK);
    }

}
