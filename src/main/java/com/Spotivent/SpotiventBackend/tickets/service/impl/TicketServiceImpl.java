package com.Spotivent.SpotiventBackend.tickets.service.impl;

import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.tickets.dto.CreateTicketRequestDTO;
import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import com.Spotivent.SpotiventBackend.tickets.entity.Tickets;
import com.Spotivent.SpotiventBackend.tickets.repository.TicketRepository;
import com.Spotivent.SpotiventBackend.tickets.service.TicketService;
import com.Spotivent.SpotiventBackend.users.entity.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventService eventService;

    public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService) {
        this.ticketRepository = ticketRepository;
        this.eventService = eventService;
    }

    @Override
    public List<TicketResponseDTO> getAllTickets(Long eventId) {
        List<Tickets> tickets;
        if (eventId != null) {
            tickets = ticketRepository.findByEventsId(eventId);
        } else {
            tickets = ticketRepository.findAll();
        }
        return tickets.stream().map(this::mapToTicketResponseDTO).collect(Collectors.toList());
    }

    @Override
    public TicketResponseDTO createTicket(CreateTicketRequestDTO request, Authentication authentication) {
        validateOrganizerRole(authentication);
        Events event = eventService.getEventById(request.getEventId());
        Tickets tickets = new Tickets();
        tickets.setType(request.getType());
        tickets.setPrice(request.getPrice());
        tickets.setAvailableSeat(request.getAvailableSeat());
        tickets.setEvents(event);
        tickets = ticketRepository.save(tickets);
        return mapToTicketResponseDTO(tickets);
    }

    @Override
    public TicketResponseDTO updateTicket(Long ticketId, CreateTicketRequestDTO request, Authentication authentication) {
        validateOrganizerRole(authentication);
        Tickets tickets = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ApplicationException("Ticket not found"));
        tickets.setType(request.getType());
        tickets.setPrice(request.getPrice());
        tickets.setAvailableSeat(request.getAvailableSeat());
        tickets = ticketRepository.save(tickets);
        return mapToTicketResponseDTO(tickets);
    }

    @Override
    public void deleteTicket(Long ticketId, Authentication authentication) {
        validateOrganizerRole(authentication);
        Tickets tickets = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ApplicationException("Ticket not found"));
        ticketRepository.delete(tickets);
    }

    private void validateOrganizerRole(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals(Roles.Organizer.name()))){
            throw new ApplicationException("Only organizers can perform this action");
        }
    }

    private TicketResponseDTO mapToTicketResponseDTO(Tickets tickets) {
        TicketResponseDTO responseDTO = new TicketResponseDTO();
        responseDTO.setId(tickets.getId());
        responseDTO.setType(tickets.getType());
        responseDTO.setPrice(tickets.getPrice());
        responseDTO.setAvailableSeat(tickets.getAvailableSeat());
        responseDTO.setEventId(tickets.getEvents().getId());
        return responseDTO;
    }
}
