package com.Spotivent.SpotiventBackend.events.service;

import com.Spotivent.SpotiventBackend.events.dto.CreateEventRequestDTO;
import com.Spotivent.SpotiventBackend.events.dto.EventResponseDTO;
import com.Spotivent.SpotiventBackend.events.entity.Events;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents();
    EventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO);
    EventResponseDTO getUserById(Long id);
    Events getDetailEvent(Long id);
}
