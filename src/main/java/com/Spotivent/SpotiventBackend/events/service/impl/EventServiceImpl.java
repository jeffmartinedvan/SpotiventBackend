package com.Spotivent.SpotiventBackend.events.service.impl;

import com.Spotivent.SpotiventBackend.events.dto.CreateEventRequestDTO;
import com.Spotivent.SpotiventBackend.events.dto.EventResponseDTO;
import com.Spotivent.SpotiventBackend.events.entity.Category;
import com.Spotivent.SpotiventBackend.events.entity.City;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.repository.CategoryRepository;
import com.Spotivent.SpotiventBackend.events.repository.CityRepository;
import com.Spotivent.SpotiventBackend.events.repository.EventRepository;
import com.Spotivent.SpotiventBackend.events.repository.EventSpecification;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.users.entity.Roles;
import com.Spotivent.SpotiventBackend.users.entity.Users;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final CityRepository cityRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, UserService userService, CityRepository cityRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Events> getAllEvents(Pageable pageable, String eventName, String city, String category, Long userId, String upcoming) {
        Specification<Events> specification = Specification.where(EventSpecification.byEventName(eventName))
                .and(EventSpecification.byCity(city))
                .and(EventSpecification.byCategory(category))
                .and(EventSpecification.byUserId(userId))
                .and(EventSpecification.byUpcoming(upcoming));
        return eventRepository.findAll(specification, pageable);
    }

    @Override
    public EventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO, String email) {
        Users users = userService.findByEmail(email);

        City city = cityRepository.findByName(createEventRequestDTO.getCityName());
        if (city == null) {
            throw new ApplicationException("City not found");
        }

        Category category = categoryRepository.findByName(createEventRequestDTO.getCategoryName());
        if (category == null) {
            throw new ApplicationException("Category not found");
        }

        Events newEvent = new Events();
        newEvent.setEventName(createEventRequestDTO.getEventName());
        newEvent.setCity(city);
        newEvent.setCategory(category);
        newEvent.setThumbnail(createEventRequestDTO.getThumbnail());
        newEvent.setImage(createEventRequestDTO.getImage());
        newEvent.setLocation(createEventRequestDTO.getLocation());
        newEvent.setDate(createEventRequestDTO.getDate());
        newEvent.setStartTime(createEventRequestDTO.getStartTime());
        newEvent.setEndTime(createEventRequestDTO.getEndTime());
        newEvent.setDescription(createEventRequestDTO.getDescription());
        newEvent.setIsFree(createEventRequestDTO.getIsFree());
        newEvent.setUsers(users);
        Events savedEvent = eventRepository.save(newEvent);
        return mapToEventResponseDTO(savedEvent);
    }

    @Override
    public Events getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public EventResponseDTO mapToEventResponseDTO(Events events) {
        EventResponseDTO responseDTO = new EventResponseDTO();
        responseDTO.setId(events.getId());
        responseDTO.setEventName(events.getEventName());
        responseDTO.setCityName(events.getCity().getName());
        responseDTO.setCategoryName(events.getCategory().getName());
        responseDTO.setThumbnail(events.getThumbnail());
        responseDTO.setImage(events.getImage());
        responseDTO.setLocation(events.getLocation());
        responseDTO.setDate(events.getDate());
        responseDTO.setStartTime(events.getStartTime());
        responseDTO.setEndTime(events.getEndTime());
        responseDTO.setDescription(events.getDescription());
        responseDTO.setIsFree(events.getIsFree());
        return responseDTO;
    }
}
