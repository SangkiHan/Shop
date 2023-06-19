package com.spring.shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
	
	public static Member createMember(String name, String city, String street, String zipcode) {
		Member member = new Member();
		member.setUsername(name);
		member.setAddress(new Address(city, street, zipcode));
		
		return member;
	}

}
