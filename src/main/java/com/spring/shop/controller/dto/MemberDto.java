package com.spring.shop.controller.dto;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.Member;

import lombok.Data;

public class MemberDto {
	
	@Data
	public static class Info{
		private Long id;
		private String name;
		private Address address;
		
		public Info() {
		}
		
		public Info(Member member) {
			this.id = member.getId();
			this.name = member.getUsername();
			this.address = member.getAddress();
		}
	}
	
	@Data
	public static class MemberList{
		private Long memberId;
		private Address address;
		private String userName;
		
		public MemberList(Member member) {
			this.memberId = member.getId();
			this.address = member.getAddress();
			this.userName = member.getUsername();
		}
	}
	
}
