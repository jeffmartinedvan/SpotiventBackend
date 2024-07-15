package com.Spotivent.SpotiventBackend.events.dto;

import com.Spotivent.SpotiventBackend.events.entity.Categories;
import com.Spotivent.SpotiventBackend.events.entity.Cities;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventResponseDTO {
    private Long id;
    private String eventName;
    private Cities cities;
    private Categories categories;
    private String thumbnail;
    private String image;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private Boolean isFree;
}
