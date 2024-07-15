package com.Spotivent.SpotiventBackend.tickets.dto;

import lombok.Data;

@Data
public class TicketResponseDTO {
    private Long id;
    private String type;
    private Long price;
    private Integer availableSeat;
    private Long eventId;
}
