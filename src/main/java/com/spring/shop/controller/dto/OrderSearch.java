package com.spring.shop.controller.dto;

import com.spring.shop.entity.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
	private String memberName;
	private OrderStatus orderStatus;
}
