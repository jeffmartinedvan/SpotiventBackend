package com.Spotivent.SpotiventBackend.events.dto;

import com.Spotivent.SpotiventBackend.events.entity.Categories;
import com.Spotivent.SpotiventBackend.events.entity.Cities;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateEventRequestDTO {
    @NotNull(message = "Event name must not be null")
    private String eventName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Cities cities;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Categories categories;

    @NotNull(message = "Thumbnail must not be null")
    private String thumbnail;

    private String image;

    @NotNull(message = "Location must not be null")
    private String location;

    @NotNull(message = "Event date must not be null")
    private LocalDate date;

    @NotNull(message = "Start time must not be null")
    private LocalTime startTime;

    @NotNull(message = "End time must not be null")
    private LocalTime endTime;

    @NotNull(message = "Description must not be null")
    private String description;

    @NotNull(message = "Is event free must not be null")
    private Boolean isFree;
}
