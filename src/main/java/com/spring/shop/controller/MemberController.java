package com.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.MemberDto;
import com.spring.shop.entity.Member;
import com.spring.shop.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping(value = "/api/v1/join")
	public void joinMember(@RequestBody MemberDto.ResgistMember request) {
		
		Member member = Member.createMember(request.getName(), request.getCity(), request.getStreet(), request.getZipcode());
		
		memberService.join(member);
	}
	
	@PostMapping(value = "/api/v1/member")
	public MemberDto.selectMember selectMember(@RequestBody Long Id) {
		
		return new MemberDto.selectMember();
	}

}
