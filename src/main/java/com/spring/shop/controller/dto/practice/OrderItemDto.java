package com.spring.shop.controller.dto.practice;

import com.spring.shop.entity.OrderItem;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderItemDto(OrderItem orderItem) {
		this.itemName = orderItem.getItem().getName();
		this.orderPrice = orderItem.getOrderPrice();
		this.count = orderItem.getCount();
	}
}
