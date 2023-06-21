package com.spring.shop.repository;

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

}
