package com.spring.shop.controller.dto;

import com.spring.shop.entity.item.Item;

import lombok.Data;

public class ItemDto {
	
	@Data
	public static class Info{
		private Long Id;
		private String name;
		private int price;
		private int stockQuantity;
		private String dtype;
		
		public Info(Item item) {
			Id = item.getId();
			this.name = item.getName();
			this.price = item.getPrice();
			this.stockQuantity = item.getStockQuantity();
		}
	}
}
