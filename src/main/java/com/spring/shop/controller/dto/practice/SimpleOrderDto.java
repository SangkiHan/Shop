package com.spring.shop.controller.dto.practice;

import java.time.LocalDateTime;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderStatus;

import lombok.Data;

@Data
public class SimpleOrderDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	
	public SimpleOrderDto(Order order) {
		this.orderId = order.getId();
		this.name = order.getMember().getUsername();
		this.orderDate = order.getOrderDate();
		this.orderStatus = order.getStatus();
		this.address = order.getDelivery().getAddress();
	}
}
