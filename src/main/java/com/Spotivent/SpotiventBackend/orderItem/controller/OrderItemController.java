package com.Spotivent.SpotiventBackend.orderItem.controller;

import com.Spotivent.SpotiventBackend.orderItem.dto.CreateOrderItemRequestDTO;
import com.Spotivent.SpotiventBackend.orderItem.dto.OrderItemResponseDTO;
import com.Spotivent.SpotiventBackend.orderItem.service.OrderItemService;
import com.Spotivent.SpotiventBackend.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response<OrderItemResponseDTO>> createOrderItem(@RequestBody CreateOrderItemRequestDTO requestDTO) {
        return Response.success("Create order item success", orderItemService.createOrderItem(requestDTO));
    }

    @GetMapping
    public ResponseEntity<Response<List<OrderItemResponseDTO>>> getOrderItemsByTransaction(@RequestParam Long transactionId) {
        return Response.success("Get order success", orderItemService.getOrderItemsByTransaction(transactionId));
    }
}