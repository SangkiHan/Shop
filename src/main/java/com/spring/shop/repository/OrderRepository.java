package com.spring.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order selectOne(Long orderId) {
		return em.find(Order.class, orderId);
	}
	
	public List<Order> selectList(Long memberId){
		return em.createQuery(
					"select o"+
					" from Order o"+
					" join o.member m"+
					" where m.id = :memberId", Order.class)
				.setParameter("memberId", memberId)
				.getResultList();
	}
}
