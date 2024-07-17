package com.Spotivent.SpotiventBackend.orderItem.dto;

import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long id;
    private Long transactionId;
    private TicketResponseDTO ticket;
    private Integer ticketQty;
}