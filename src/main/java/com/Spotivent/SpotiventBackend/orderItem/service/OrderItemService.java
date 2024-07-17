package com.Spotivent.SpotiventBackend.orderItem.service;

import com.Spotivent.SpotiventBackend.orderItem.dto.CreateOrderItemRequestDTO;
import com.Spotivent.SpotiventBackend.orderItem.dto.OrderItemResponseDTO;
import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;

import java.util.List;
import java.util.Set;

public interface OrderItemService {
    OrderItemResponseDTO createOrderItem(CreateOrderItemRequestDTO requestDTO);
    List<OrderItemResponseDTO> getOrderItemsByTransaction(Long transactionId);
    Set<OrderItems> getOrderItemsByIds(Set<Long> orderItemIds);
    void saveOrderItem(OrderItems orderItem);
}