package com.spring.shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private long id;
	private String username;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	public Member createMember(String name, Address address) {
		Member member = new Member();
		member.setUsername(name);
		member.setAddress(address);
		return member;
	}
	
	public void updateMember(String name, Address address) {
		this.setUsername(name);
		this.setAddress(address);
	}
}
