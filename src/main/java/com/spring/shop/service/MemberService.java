package com.spring.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.controller.dto.MemberDto;
import com.spring.shop.entity.Member;
import com.spring.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository; 
	
	
	public void join(MemberDto.Info request) {
		Member member = new Member();
		memberRepository.join(member.createMember(request.getName(), request.getAddress()));
	}
	
	@Transactional
	public void update(MemberDto.Info request) {
		Member member = memberRepository.selectOne(request.getId());
		member.updateMember(request.getName(), request.getAddress());
	}
	
	public MemberDto.Info selectOne(Long id){
		Member result = memberRepository.selectOne(id);
		return new MemberDto.Info(result);
	}
	
	public List<MemberDto.MemberList> selectMemberList() {
		List<Member> result = memberRepository.selectMemberList();
		
		return result.stream()
				.map(o -> new MemberDto.MemberList(o))
				.collect(Collectors.toList());
	}
}
