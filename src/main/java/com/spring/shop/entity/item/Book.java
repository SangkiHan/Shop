package com.spring.shop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spring.shop.controller.dto.ItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("B")
@Entity
public class Book extends Item {
	
	private String author;
	private String isbn;
}
