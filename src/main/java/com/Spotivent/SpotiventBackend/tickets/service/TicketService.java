package com.Spotivent.SpotiventBackend.tickets.service;

import com.Spotivent.SpotiventBackend.tickets.dto.CreateTicketRequestDTO;
import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TicketService {
    List<TicketResponseDTO> getAllTickets(Long eventId);
    TicketResponseDTO createTicket(CreateTicketRequestDTO request, Authentication authentication);
    TicketResponseDTO updateTicket(Long ticketId, CreateTicketRequestDTO request, Authentication authentication);
    void deleteTicket(Long ticketId, Authentication authentication);
}
