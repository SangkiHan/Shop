package com.spring.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.controller.dto.OrderDto;
import com.spring.shop.entity.Delivery;
import com.spring.shop.entity.Member;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderItem;
import com.spring.shop.entity.item.Item;
import com.spring.shop.repository.ItemRepository;
import com.spring.shop.repository.MemberRepository;
import com.spring.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final ItemRepository itemRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public void save(OrderDto.Request request) {
		
		Order order = new Order();
		List<OrderItem> orderItems  = new ArrayList<OrderItem>();
		Member member = memberRepository.selectOne(request.getMemberId());
		
		for(OrderDto.Item items : request.getItem()) {
			Item item = itemRepository.selectOne(items.getItemId());
			OrderItem orderItem = new OrderItem();
			orderItem.createOrderItem(item, items.getCount());
			orderItems.add(orderItem);
		}
		
		Delivery delivery = new Delivery(); 
		delivery.createOrder(member.getAddress());
		
		order.createOrder(member, orderItems, delivery);
		
		orderRepository.save(order);
	}
	
	@Transactional
	public void cancel(Long orderId) {
		Order order = selectOne(orderId);
		order.cancelOrder();
	}
	
	public Order selectOne(Long orderId) {
		return orderRepository.selectOne(orderId);
	}
	
	public List<OrderDto.Info> selectList(Long memberId){
		List<Order> orderList = orderRepository.selectList(memberId);
		
		List<OrderDto.Info> orderInfos = orderList.stream()
				.map(o -> new OrderDto.Info(o))
				.collect(Collectors.toList());
		
		return orderInfos;
	}
}
