package com.spring.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.controller.dto.ItemDto;
import com.spring.shop.entity.item.Album;
import com.spring.shop.entity.item.Book;
import com.spring.shop.entity.item.Item;
import com.spring.shop.entity.item.Movie;
import com.spring.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void save(ItemDto.Info request) {
		Item item = null;
		String dtype = request.getDtype();
		if("A".equals(dtype)) {
			item = new Album();
		}
		else if("B".equals(dtype)){
			item = new Book();
		}
		else if("M".equals(dtype)){
			item = new Movie();
		}
		item.createItem(request);
		itemRepository.save(item);
	}
	
	public List<ItemDto.Info> selectList(){
		List<Item> result = itemRepository.selectList();
		
		List<ItemDto.Info> infos = result.stream()
				.map(o -> new ItemDto.Info(o))
				.collect(Collectors.toList());
		
		return infos;
	}
}
