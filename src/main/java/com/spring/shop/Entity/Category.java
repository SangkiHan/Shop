package com.spring.shop.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Category {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name = "category_id")
	private Long Id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<CategoryItem> categoryItem = new ArrayList<>();
}
