package com.spring.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
	public void createOrderItem(Item item, int count) {
		this.item = item;
		this.orderPrice = count*item.getPrice();
		this.count = count;
		item.removeStock(count);
	}
}
