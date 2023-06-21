package com.spring.shop.controller.dto;

import com.spring.shop.entity.OrderItem;

import lombok.Data;

@Data
public class OrderItemDto {
	private Long Id;
	private ItemDto.Info item;
	private int orderPrice;
	private int count;
	
	public OrderItemDto(OrderItem orderItem) {
		Id = orderItem.getId();
		this.item =  new ItemDto.Info(orderItem.getItem());
		this.orderPrice = orderItem.getOrderPrice();
		this.count = orderItem.getCount();
	}
}
