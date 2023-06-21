package com.spring.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.shop.exception.GlobalException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
	
	@javax.persistence.Id @GeneratedValue
	@Column(name="orders_id")
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_Id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public void createOrder(Member member, List<OrderItem> orderItems, Delivery delivery) {
		this.member = member;
		this.orderItems = orderItems;
		this.delivery = delivery;
		this.status = OrderStatus.ORDER;
		this.orderDate = LocalDateTime.now();
		delivery.setOrder(this);
		member.getOrders().add(this);
		for(OrderItem orderitem : orderItems) {
			orderitem.setOrder(this);
		}
	}
	
	public void cancelOrder() {
		if(DeliveryStatus.COMP.equals(this.delivery.getStatus())) {
			throw new GlobalException("ORDERERROR","배송이 완료된 건은 취소가 불가능 합니다.");
		}
		for(OrderItem orderItem : this.orderItems) {
			orderItem.getItem().addStock(orderItem.getCount());
		}
		this.status = OrderStatus.CANCEL;
	}
	
}
