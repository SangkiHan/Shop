package com.spring.shop.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.spring.shop.entity.Member;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderStatus;

import lombok.Data;

public class OrderDto {
	
	@Data
	public static class Request{
		private Long memberId;
		private	List<Item> item;
	}
	
	@Data
	public static class Item{
		private Long itemId;
		private int count;
	}
	
	@Data
	public static class Info{
		private Long orderId;
		private String name;
		private List<OrderItemDto> orderItems = new ArrayList<>();
		private DeliveryDto delivery;
		private LocalDateTime orderDate;
		private OrderStatus status;
		
		public Info(Order order) {
			this.orderId = order.getId();
			this.name = order.getMember().getUsername();
			this.delivery =  new DeliveryDto(order.getDelivery());
			this.orderDate = order.getOrderDate();
			this.status = order.getStatus();
			this.orderItems = order.getOrderItems().stream()
					.map(o -> new OrderItemDto(o))
					.collect(Collectors.toList());
		}
	}
}
