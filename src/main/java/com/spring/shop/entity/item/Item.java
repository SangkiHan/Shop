package com.spring.shop.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.spring.shop.controller.dto.ItemDto;
import com.spring.shop.entity.CategoryItem;
import com.spring.shop.exception.GlobalException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Entity
public abstract class Item {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "item_id")
	private Long Id;
	
	@OneToMany(mappedBy = "item")
	private List<CategoryItem> categoryItems = new ArrayList<>();
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int resStock = this.stockQuantity - quantity;
		if(resStock<0) {
			throw new GlobalException("수량이 부족합니다.");
		}
		this.stockQuantity = resStock;
	}
}
