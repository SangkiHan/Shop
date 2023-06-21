package com.spring.shop.controller.dto;

import java.util.List;

import lombok.Data;

public class OrderDto {
	
	@Data
	public static class Request{
		private Long memberId;
		private	List<Item> item;
	}
	
	@Data
	public static class Item{
		private Long itemId;
		private int count;
	}
}
