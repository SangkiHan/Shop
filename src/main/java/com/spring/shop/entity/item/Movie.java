package com.spring.shop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spring.shop.controller.dto.ItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("M")
@Entity
public class Movie extends Item{
	
	private String director;
	private String actor;
}
