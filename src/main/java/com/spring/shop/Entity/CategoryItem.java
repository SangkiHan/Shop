package com.spring.shop.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.shop.Entity.Item.Item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CategoryItem {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "category_item_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
}
