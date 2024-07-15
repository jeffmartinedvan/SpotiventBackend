package com.Spotivent.SpotiventBackend.events.dto;

import com.Spotivent.SpotiventBackend.events.entity.Category;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventResponseDTO {
    private Long id;
    private String eventName;
    private String cityName;
    private String categoryName;
    private String thumbnail;
    private String image;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private Boolean isFree;
}
