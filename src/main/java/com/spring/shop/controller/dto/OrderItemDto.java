package com.spring.shop.controller.dto;

import com.spring.shop.entity.OrderItem;
import com.spring.shop.entity.item.Item;

import lombok.Data;

@Data
public class OrderItemDto {
	private Long Id;
	private Item item;
	private int orderPrice;
	private int count;
	
	public OrderItemDto(OrderItem orderItem) {
		Id = orderItem.getId();
		this.item = orderItem.getItem();
		this.orderPrice = orderItem.getOrderPrice();
		this.count = orderItem.getCount();
	}
}
