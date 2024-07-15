package com.Spotivent.SpotiventBackend.tickets.controller;

import com.Spotivent.SpotiventBackend.response.Response;
import com.Spotivent.SpotiventBackend.tickets.dto.CreateTicketRequestDTO;
import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import com.Spotivent.SpotiventBackend.tickets.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<Response<List<TicketResponseDTO>>> getAllTickets(@RequestParam(name = "eventId", required = false) Long eventId) {
        return Response.success("Get all tickets success", ticketService.getAllTickets(eventId));
    }

    @PostMapping
    public ResponseEntity<Response<TicketResponseDTO>> createTicket(@RequestBody CreateTicketRequestDTO request, Authentication authentication) {
        return Response.success("Ticket created successfully", ticketService.createTicket(request, authentication));
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Response<TicketResponseDTO>> updateTicket(@PathVariable Long ticketId, @RequestBody CreateTicketRequestDTO request, Authentication authentication) {
        return Response.success("Ticket updated successfully", ticketService.updateTicket(ticketId, request, authentication));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Response<Void>> deleteTicket(@PathVariable Long ticketId, Authentication authentication) {
        ticketService.deleteTicket(ticketId, authentication);
        return Response.success("Ticket deleted successfully", null);
    }
}
