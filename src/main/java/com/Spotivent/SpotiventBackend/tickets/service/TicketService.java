package com.Spotivent.SpotiventBackend.tickets.service;

import com.Spotivent.SpotiventBackend.tickets.dto.CreateTicketRequestDTO;
import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import com.Spotivent.SpotiventBackend.tickets.entity.Tickets;

import java.util.List;

public interface TicketService {
    List<TicketResponseDTO> getAllTickets(Long eventId);
    TicketResponseDTO createTicket(CreateTicketRequestDTO request);
    TicketResponseDTO updateTicket(Long ticketId, CreateTicketRequestDTO request);
    TicketResponseDTO findTicketById(Long ticketId);
    Tickets getDetailTicket(Long ticketId);
    void deleteTicket(Long ticketId);
}
