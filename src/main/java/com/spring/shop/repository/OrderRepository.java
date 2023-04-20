package com.spring.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Order;

@Repository
public class OrderRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long save(Order order) {
		em.persist(order);
		return order.getId();
	}
	
	public Order findOne(Long orderId) {
		return em.createQuery("select o from Order o where o.Id = :orderId", Order.class)
				.setParameter("orderId", orderId)
				.getSingleResult();
	}

}
