package com.spring.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.OrderSearch;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderItem;
import com.spring.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
	
	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1(OrderSearch orderSearch){
		List<Order> all = orderRepository.findAllByString(orderSearch);
		for(Order order : all) {
			order.getMember().getUsername();
			order.getDelivery().getAddress();
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.stream().forEach(o -> o.getItem().getName());
		}
		return all;
	}
}
