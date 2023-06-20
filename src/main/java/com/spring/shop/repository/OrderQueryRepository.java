package com.spring.shop.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.spring.shop.controller.dto.OrderFlatDto;
import com.spring.shop.controller.dto.OrderItemQueryDto;
import com.spring.shop.controller.dto.OrderQueryDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
	
	private final EntityManager em;
	
	public List<OrderQueryDto> findOrderQueryDtos(){
		List<OrderQueryDto> result = findOrders();
		
		result.forEach(o -> {
			List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
			o.setOrderItems(orderItems);
		});
		
		return result;
	}
	
	public List<OrderQueryDto> findAllByDto_optimizatioin(){
		List<OrderQueryDto> result = findOrders();
		
		List<Long> orderIds =  result.stream()
				.map(o -> o.getOrderId())
				.collect(Collectors.toList());
		
		Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderMap(orderIds);
		
		result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
		
		return result;
	}
	
	public Map<Long, List<OrderItemQueryDto>> findOrderMap(List<Long> orderIds){
		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new com.spring.shop.controller.dto.OrderItemQueryDto(oi.order.Id, i.name, oi.orderPrice, oi.count)"+
				" from OrderItem oi"+
				" join oi.item i"+
				" where oi.order.Id in :orderIds", OrderItemQueryDto.class)
		.setParameter("orderIds", orderIds)
		.getResultList();

		return orderItems.stream()
				.collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
	}
	
	public List<OrderItemQueryDto> findOrderItems(Long orderId){
		return em.createQuery("select new com.spring.shop.controller.dto.OrderItemQueryDto(oi.order.Id, i.name, oi.orderPrice, oi.count)"+
						" from OrderItem oi"+
						" join oi.item i"+
						" where oi.order.Id = :orderId", OrderItemQueryDto.class)
				.setParameter("orderId", orderId)
				.getResultList();
	}
	
	public List<OrderQueryDto> findOrders(){
		return em.createQuery(
						"select new com.spring.shop.controller.dto.OrderQueryDto(o.Id, m.username,o.orderDate,o.status,d.address)"+
						" from Order o"+
						" join o.member m"+
						" join o.delivery d", OrderQueryDto.class)
			.getResultList();
	}
	
	public List<OrderFlatDto> findAllByDto_flat(){
		return em.createQuery(
						"select new com.spring.shop.controller.dto.OrderFlatDto(o.Id, m.username,o.orderDate,o.status,d.address, i.name, oi.orderPrice, oi.count)"+
						" from Order o"+
						" join o.member m"+
						" join o.delivery d" +
						" join o.orderItems oi"+
						" join oi.item i", OrderFlatDto.class)
			.getResultList();
	}
}
