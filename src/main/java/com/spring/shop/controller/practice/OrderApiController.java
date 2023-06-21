package com.spring.shop.controller.practice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.practice.OrderFlatDto;
import com.spring.shop.controller.dto.practice.OrderQueryDto;
import com.spring.shop.controller.dto.practice.OrderSearch;
import com.spring.shop.controller.dto.practice.OrderSimpleDto;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderItem;
import com.spring.shop.repository.OrderQueryRepository;
import com.spring.shop.repository.OrderSimpleRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
	
	private final OrderSimpleRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;
	
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
	
	@GetMapping("/api/v2/orders")
	public List<OrderSimpleDto> ordersV2(OrderSearch orderSearch){
		List<Order> orders = orderRepository.findAllByString(orderSearch);
		List<OrderSimpleDto> collect = orders.stream()
				.map(o -> new OrderSimpleDto(o))
				.collect(Collectors.toList());
		return collect;
	}
	
	@GetMapping("/api/v3/orders")
	public List<OrderSimpleDto> ordersV3(OrderSearch orderSearch){
		List<Order> orders = orderRepository.findAllWithItem(orderSearch);
		List<OrderSimpleDto> collect = orders.stream()
				.map(o -> new OrderSimpleDto(o))
				.collect(Collectors.toList());
		return collect;
	}
	
	@GetMapping("/api/v3.1/orders")
	public List<OrderSimpleDto> ordersV31(@RequestParam(value = "offset", defaultValue = "0") int offset,
									@RequestParam(value = "limit", defaultValue = "100") int limit ){
		List<Order> orders = orderRepository.findAllWithMemberDeliveyPaging(offset, limit);
		
		List<OrderSimpleDto> collect = orders.stream()
				.map(o -> new OrderSimpleDto(o))
				.collect(Collectors.toList());
		return collect;
	}
	
	/*
	 * 쿼리가 1대N으로 생성됌
	 * */
	@GetMapping("/api/v4/orders")
	public List<OrderQueryDto> ordersV4(){
		List<OrderQueryDto> orders = orderQueryRepository.findOrderQueryDtos();
		
		return orders;
	}
	
	/*
	 * 쿼리가 2개로 해결됌
	 * */
	@GetMapping("/api/v5/orders")
	public List<OrderQueryDto> ordersV5(){
		List<OrderQueryDto> orders = orderQueryRepository.findAllByDto_optimizatioin();
		
		return orders;
	}
	
	/*
	 * 쿼리1번으로 해결할 수 있지만 중복데이터를 걸러내는 작업을 추가해야하고, 페이징이 불가하다.
	 * */
	@GetMapping("/api/v6/orders")
	public List<OrderFlatDto> ordersV6(){
		List<OrderFlatDto> orders = orderQueryRepository.findAllByDto_flat();
		
		return orders;
	}
}
