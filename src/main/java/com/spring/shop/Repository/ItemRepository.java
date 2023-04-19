package com.spring.shop.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.Entity.Item.Item;

@Repository
public class ItemRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/*
	 * 상품저장
	 * */
	public void save(Item item) {
		if(item.getId()==null) {
			em.persist(item);
		}else {
			em.merge(item);
		}
	}
	
	/*
	 * 상품조회
	 * */
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	/*
	 * 상품리스트 조회
	 * */
	public List<Item> findList(){
		return em.createQuery("select I from Item I", Item.class)
				.getResultList();
	}

}
