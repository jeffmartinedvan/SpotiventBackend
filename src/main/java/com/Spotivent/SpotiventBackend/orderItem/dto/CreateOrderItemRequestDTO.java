package com.Spotivent.SpotiventBackend.orderItem.dto;

import lombok.Data;

@Data
public class CreateOrderItemRequestDTO {
    private Long ticketId;
    private Integer ticketQty;
}