package com.spring.shop.controller.dto.practice;

import java.time.LocalDateTime;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderFlatDto {
	
	private Long orderId;
	private String name; 
	private LocalDateTime localDateTime;
	private OrderStatus orderStatus;
	private Address address;
	
	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderFlatDto(Long orderId, String name, LocalDateTime localDateTime, OrderStatus orderStatus,
			Address address, String itemName, int orderPrice, int count) {
		this.orderId = orderId;
		this.name = name;
		this.localDateTime = localDateTime;
		this.orderStatus = orderStatus;
		this.address = address;
		this.itemName = itemName;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
