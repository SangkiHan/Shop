package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.entity.item.Item;
import com.spring.shop.repository.ItemRepository;

@Service
@Transactional(readOnly = true)
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/*
	 * 상품등록
	 * */
	@Transactional
	public Long save(Item item) {
		itemRepository.save(item);
		return item.getId();
	}
	
	/*
	 * 상품조회
	 * */
	public Item findOne(Item item) {
		return itemRepository.findOne(item.getId());
	}
	
	/*
	 * 상품리스트조회
	 * */
	public List<Item> findList(){
		return itemRepository.findList();
	}

}
