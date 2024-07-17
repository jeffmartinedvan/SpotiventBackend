package com.Spotivent.SpotiventBackend.orderItem.dto;

import lombok.Data;

@Data
public class CreateOrderItemRequestDTO {
    private Long transactionId;
    private Long ticketId;
    private Integer ticketQty;
}