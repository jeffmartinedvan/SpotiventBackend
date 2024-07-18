package com.Spotivent.SpotiventBackend.events.service;

import com.Spotivent.SpotiventBackend.events.dto.CreateEventRequestDTO;
import com.Spotivent.SpotiventBackend.events.dto.EventResponseDTO;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Page<Events> getAllEvents(Pageable pageable, String eventName, String city, String category, Long userId, String upcoming);
    EventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO);
    Events getEventById(Long eventId);
}
