package com.Spotivent.SpotiventBackend.orderItem.service.impl;

import com.Spotivent.SpotiventBackend.exception.ApplicationException;
import com.Spotivent.SpotiventBackend.orderItem.dto.CreateOrderItemRequestDTO;
import com.Spotivent.SpotiventBackend.orderItem.dto.OrderItemResponseDTO;
import com.Spotivent.SpotiventBackend.orderItem.entity.OrderItems;
import com.Spotivent.SpotiventBackend.orderItem.repository.OrderItemRepository;
import com.Spotivent.SpotiventBackend.orderItem.service.OrderItemService;
import com.Spotivent.SpotiventBackend.tickets.entity.Tickets;
import com.Spotivent.SpotiventBackend.tickets.service.TicketService;
import com.Spotivent.SpotiventBackend.transactions.services.TransactionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final TicketService ticketService;
    private final TransactionService transactionService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, TicketService ticketService, @Lazy TransactionService transactionService) {
        this.orderItemRepository = orderItemRepository;
        this.ticketService = ticketService;
        this.transactionService = transactionService;
    }

    @Override
    public OrderItemResponseDTO createOrderItem(CreateOrderItemRequestDTO requestDTO) {
        Tickets ticket = ticketService.getDetailTicket(requestDTO.getTicketId());
        OrderItems orderItem = new OrderItems();
        orderItem.setTransactions(transactionService.getDetailById(requestDTO.getTransactionId()));
        orderItem.setTickets(ticket);
        orderItem.setTicketQty(requestDTO.getTicketQty());

        return mapToOrderItemResponseDTO(orderItemRepository.save(orderItem));
    }

    @Override
    public List<OrderItemResponseDTO> getOrderItemsByTransaction(Long transactionId) {
        List<OrderItems> orderItems = orderItemRepository.findAll()
                .stream()
                .filter(orderItem -> orderItem.getTransactions().getId().equals(transactionId))
                .toList();

        return orderItems.stream().map(this::mapToOrderItemResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Set<OrderItems> getOrderItemsByIds(Set<Long> orderItemIds) {
        return new HashSet<>(orderItemRepository.findAllById(orderItemIds));
    }

    @Override
    public void saveOrderItem(OrderItems orderItem) {
        orderItemRepository.save(orderItem);
    }

    private OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItems orderItem) {
        OrderItemResponseDTO responseDTO = new OrderItemResponseDTO();
        responseDTO.setId(orderItem.getId());
        responseDTO.setTransactionId(orderItem.getTransactions().getId());
        responseDTO.setTicket(ticketService.findTicketById(orderItem.getTickets().getId()));
        responseDTO.setTicketQty(orderItem.getTicketQty());
        return responseDTO;
    }
}