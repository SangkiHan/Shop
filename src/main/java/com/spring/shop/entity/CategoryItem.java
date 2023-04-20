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
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CategoryItem {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "category_item_id")
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	public void setCategory(Category category) {
		this.category = category;
		category.getCategoryItem().add(this);
	}
	
	public void setItem(Item item) {
		this.item = item;
		item.getCategoryItems().add(this);
	}
	
}
