package com.spring.shop.Entity.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

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
