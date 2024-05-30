package org.ticketshop.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.dto.ReservedTicketDTO;
import org.ticketshop.dto.CancelTicketDTO;
import org.ticketshop.dto.SendReserveInfoDTO;
import org.ticketshop.dto.TicketDTO;
import org.ticketshop.mapper.SendReserveInfoMapper;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Ticket;
import org.ticketshop.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final SendReserveInfoMapper sendReserveInfoMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper, SendReserveInfoMapper sendReserveInfoMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
        this.sendReserveInfoMapper = sendReserveInfoMapper;
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

    @PostMapping("/reserveTickets")
    public ResponseEntity<ReservedTicketDTO> reserveTickets(@RequestBody SendReserveInfoDTO sendReserveInfoDTO) {
        return new ResponseEntity<>(ticketService.reserveTickets(sendReserveInfoDTO),
                                    HttpStatus.OK);
    }

    @PostMapping("/buyReservedTickets")
    public ResponseEntity<List<TicketDTO>> buyReservedTickets(@RequestParam Long userId) {
        return new ResponseEntity<>(ticketService.buyReservedTickets(userId), HttpStatus.OK);
     }

     @DeleteMapping("/cancelTickets")
    public ResponseEntity<List<Ticket>> cancelTickets(@RequestBody CancelTicketDTO cancelTicketDTO) {
        return new ResponseEntity<>(ticketService.cancelTickets(cancelTicketDTO.userId(), cancelTicketDTO.manifestationId()), HttpStatus.OK);
     }

}
