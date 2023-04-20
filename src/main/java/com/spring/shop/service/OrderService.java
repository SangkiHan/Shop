package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.entity.Delivery;
import com.spring.shop.entity.Member;
import com.spring.shop.entity.Order;
import com.spring.shop.entity.OrderItem;
import com.spring.shop.entity.item.Item;
import com.spring.shop.repository.ItemRepository;
import com.spring.shop.repository.MemberRepository;
import com.spring.shop.repository.OrderRepository;

@Service
@Transactional(readOnly = true)
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		Member member = memberRepository.find(memberId);
		
		Item item = itemRepository.findOne(itemId);
		
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		Order order = Order.createOrder(member, delivery, orderItem);
		
		orderRepository.save(order);
		
		return order.getId();
	}
	
	@Transactional
	public void cancel(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancel();
	}

}
