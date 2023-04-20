package com.spring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shop.entity.Member;
import com.spring.shop.repository.MemberRepository;


@SpringBootTest
public class TestMember {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@org.junit.jupiter.api.Test
	@Transactional
	void Test(){
		
		Member member = new Member();
		member.setUsername("상기");
		
		Long id = memberRepository.save(member);
		
		Member findMember = memberRepository.find(id);
		
		System.out.println("Member : "+findMember.toString());
	}

}
