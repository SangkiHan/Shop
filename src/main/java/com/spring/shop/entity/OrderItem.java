package com.spring.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.shop.entity.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
	public static OrderItem createOrderItem(Item item, int orderPrice, int quantity) {
		OrderItem orderItem = new OrderItem();
		orderItem.setCount(quantity);
		orderItem.setItem(item);
		orderItem.setOrderPrice(item.getPrice());
		
		item.removeStock(quantity);
		
		return orderItem;
	}
	
	public void cancel() {
		getItem().addStock(count);
	}
}
