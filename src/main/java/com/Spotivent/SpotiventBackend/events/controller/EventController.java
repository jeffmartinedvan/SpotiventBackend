package com.Spotivent.SpotiventBackend.events.controller;

import com.Spotivent.SpotiventBackend.auth.helper.Claims;
import com.Spotivent.SpotiventBackend.events.dto.CreateEventRequestDTO;
import com.Spotivent.SpotiventBackend.events.dto.EventResponseDTO;
import com.Spotivent.SpotiventBackend.events.entity.Events;
import com.Spotivent.SpotiventBackend.events.service.EventService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/event")
@Validated
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Response<Page<Events>>> getAllEvents(
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String upcoming,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return Response.success("All events fetched", eventService.getAllEvents(pageable,
                eventName, city, category, userId, upcoming));
    }

    @PostMapping
    public ResponseEntity<Response<EventResponseDTO>> createEvent(@RequestBody CreateEventRequestDTO createEventRequestDTO) {
        String email = (String) Claims.getClaims().get("sub");
        return Response.success("Event created successfully", eventService.createEvent(createEventRequestDTO,email));
    }
}
