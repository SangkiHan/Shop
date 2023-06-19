package com.spring.shop.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class MemberDto {
	
	@Setter
	@Getter
	@ToString
	public static class ResgistMember{
		private String name;
		private String street;
		private String city;
		private String zipcode;
	}
	
	@Getter
	@Setter
	@ToString
	public static class selectMember{
		private Long Id;
		private String name;
		private String street;
		private String city;
		private String zipcode;
	}

}
