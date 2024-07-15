package com.Spotivent.SpotiventBackend.orderItem.service;

import com.Spotivent.SpotiventBackend.orderItem.dto.CreateOrderItemRequestDTO;
import com.Spotivent.SpotiventBackend.orderItem.dto.OrderItemResponseDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDTO createOrderItem(CreateOrderItemRequestDTO requestDTO, Long transactionId);
    List<OrderItemResponseDTO> getOrderItemsByTransaction(Long transactionId);
}