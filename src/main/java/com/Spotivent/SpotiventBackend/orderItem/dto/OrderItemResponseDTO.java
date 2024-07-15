package com.Spotivent.SpotiventBackend.orderItem.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long id;
    private Long ticketId;
    private Integer ticketQty;
    private Long transactionId;
}