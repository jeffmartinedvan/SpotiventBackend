package com.Spotivent.SpotiventBackend.events.service.impl;

import com.Spotivent.SpotiventBackend.events.dto.CreateEventRequestDTO;
import com.Spotivent.SpotiventBackend.events.dto.EventResponseDTO;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.repository.EventRepository;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO) {
        Events newEvent = new Events();
        newEvent.setEventName(createEventRequestDTO.getEventName());
        newEvent.setCities(createEventRequestDTO.getCities());
        newEvent.setCategories(createEventRequestDTO.getCategories());
        newEvent.setThumbnail(createEventRequestDTO.getThumbnail());
        newEvent.setImage(createEventRequestDTO.getImage());
        newEvent.setLocation(createEventRequestDTO.getLocation());
        newEvent.setDate(createEventRequestDTO.getDate());
        newEvent.setStartTime(createEventRequestDTO.getStartTime());
        newEvent.setEndTime(createEventRequestDTO.getEndTime());
        newEvent.setDescription(createEventRequestDTO.getDescription());
        newEvent.setIsFree(createEventRequestDTO.getIsFree());
        Events savedEvent = eventRepository.save(newEvent);


        return null;
    }

    @Override
    public EventResponseDTO getUserById(Long id) {
        Events events = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        EventResponseDTO response = new EventResponseDTO();
        response.setId(events.getId());
        response.setEventName(events.getEventName());
        response.setCities(events.getCities());
        response.setCategories(events.getCategories());
        response.setThumbnail(events.getThumbnail());
        response.setImage(events.getImage());
        response.setLocation(events.getLocation());
        response.setDate(events.getDate());
        response.setStartTime(events.getStartTime());
        response.setEndTime(events.getEndTime());
        response.setDescription(events.getDescription());
        response.setIsFree(events.getIsFree());
        return null;
    }

    @Override
    public Events getDetailEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
