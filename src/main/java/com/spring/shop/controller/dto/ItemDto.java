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
		
		public Info(Item item) {
			Id = item.getId();
			this.name = item.getName();
			this.price = item.getPrice();
			this.stockQuantity = item.getStockQuantity();
		}
	}
	
	@Data
	public static class Book{
		private Long Id;
		private String name;
		private int price;
		private int stockQuantity;
		private String author;
		private String isbn;
		
		public Book(com.spring.shop.entity.item.Book o) {
			Id = o.getId();
			this.name = o.getName();
			this.price = o.getPrice();
			this.stockQuantity = o.getStockQuantity();
			this.author = o.getAuthor();
			this.isbn = o.getIsbn();
		}
	}
}
