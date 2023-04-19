package com.spring.shop.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.shop.Entity.Item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "orders_id")
	private Order order;
	
	private int orderPrice;
	
	private int count;

}
