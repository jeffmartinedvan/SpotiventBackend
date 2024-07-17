package com.Spotivent.SpotiventBackend.orderItem.dto;

import com.Spotivent.SpotiventBackend.tickets.dto.TicketResponseDTO;
import lombok.Data;

@Data
public class CreateOrderItemRequestDTO {
    private Long transactionId;
    private Long ticketId;
    private Integer ticketQty;
}