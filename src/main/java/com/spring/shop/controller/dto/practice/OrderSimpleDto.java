package com.spring.shop.controller.dto.practice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderSimpleDto {
	
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	private List<OrderItemDto> orderItems;
	
	public OrderSimpleDto(Order order) {
		this.orderId = order.getId();
		this.name = order.getMember().getUsername();
		this.orderDate = order.getOrderDate();
		this.orderStatus = order.getStatus();
		this.address = order.getDelivery().getAddress();
		this.orderItems = order.getOrderItems().stream()
				.map(orderItem -> new OrderItemDto(orderItem))
				.collect(Collectors.toList());
	}
}
