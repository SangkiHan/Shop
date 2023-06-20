package com.spring.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.MemberDto;
import com.spring.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/*
	 * 회원가입
	 * */
	@PostMapping("/join")
	public void join(@RequestBody MemberDto.Info member) throws Exception{
		memberService.join(member);
	}
	
	/*
	 * 회원수정
	 * */
	@PostMapping("/update")
	public void update(@RequestBody MemberDto.Info member) throws Exception{
		memberService.update(member);
	}
	
	/*
	 * 회원상세
	 * */
	@PostMapping("/selectOne")
	public MemberDto.Info selectOne(@RequestParam("id") Long id) throws Exception{
		return memberService.selectOne(id);
	}
	
	/*
	 * 회원목록
	 * */ 
	@GetMapping("/selectList")
	public List<MemberDto.MemberList> selectMemberList() throws Exception{
		return memberService.selectMemberList();
	}
}
