package com.spring.shop.controller.dto;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.Delivery;
import com.spring.shop.entity.DeliveryStatus;

import lombok.Data;

@Data
public class DeliveryDto {
	private Long Id;
	private Address address;
	private DeliveryStatus status;
	
	public DeliveryDto(Delivery delivery) {
		Id = delivery.getId();
		this.address = delivery.getAddress();
		this.status = delivery.getStatus();
	}
}
