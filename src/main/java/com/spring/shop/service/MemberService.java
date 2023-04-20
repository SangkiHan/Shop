package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.entity.Member;
import com.spring.shop.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository; 
	
	/*
	 * 회원가입
	 * */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	/*
	 * 회원리스트조회
	 * */
	public List<Member> findAll(){
		return memberRepository.findAll();
	}
	
	/*
	 * 회원조회
	 * */
	public Member findOne(Member member) {
		return memberRepository.find(member.getId());
	}
	
	/*
	 * 회원가입시 중복체크
	 * */
	private void validateDuplicateMember(Member member) {
		List<Member> findMember = memberRepository.findByUsername(member.getUsername());
		if(!findMember.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
}
