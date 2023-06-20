package com.spring.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.ItemDto;
import com.spring.shop.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@PostMapping("/save")
	public void reist(@RequestBody ItemDto.Info request) {
		itemService.save(request);
	}
	
	@GetMapping("/selectList")
	public List<ItemDto.Info> selectOne() {
		return itemService.selectList();
	}
}
