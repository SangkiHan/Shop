package com.spring.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.OrderSearch;
import com.spring.shop.controller.dto.OrderSimpleQueryDto;
import com.spring.shop.controller.dto.SimpleOrderDto;
import com.spring.shop.entity.Order;
import com.spring.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderSimpleController {
	
	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersV1(OrderSearch orderSearch){
		List<Order> all = orderRepository.findAllByString(orderSearch);
		return all;
	}
	
	/*
	 * Entity -> DTO 변환
	 * 단점 : LAZY초기화로 인한 쿼리가 많이 발생하여 성능 저하가 생김
	 * */
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2(OrderSearch orderSearch){
		List<Order> orders = orderRepository.findAllByString(orderSearch);
		List<SimpleOrderDto> result = orders.stream()
				.map(o -> new SimpleOrderDto(o))
				.collect(Collectors.toList());
		return result;
	}
	
	@GetMapping("/api/v3/simple-orders")
	public List<SimpleOrderDto> ordersV3(OrderSearch orderSearch){
		List<Order> orders = orderRepository.findAllWithMemberDelivey(orderSearch);
		List<SimpleOrderDto> result = orders.stream()
				.map(o -> new SimpleOrderDto(o))
				.collect(Collectors.toList());
		return result;
	}
	
	@GetMapping("/api/v4/simple-orders")
	public List<OrderSimpleQueryDto> ordersV4(OrderSearch orderSearch){
		List<OrderSimpleQueryDto> orders = orderRepository.findOrderDtos(orderSearch);
		return orders;
	}
}
