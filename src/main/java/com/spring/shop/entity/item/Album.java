package com.spring.shop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.spring.shop.controller.dto.ItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("A")
@Entity
public class Album extends Item {
	private String artist;
	private String etc;
}
