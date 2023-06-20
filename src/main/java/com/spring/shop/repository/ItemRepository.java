package com.spring.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.entity.item.Item;

@Repository
public class ItemRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Item item) {
		if(item.getId()==null) {
			em.persist(item);
		}
		else {
			em.merge(item);
		}
	}
	
	public List<Item> selectList() {
		return em.createQuery(
				"select i"+
				" from Item i", Item.class)
			.getResultList();
	}
}
