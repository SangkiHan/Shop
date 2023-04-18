package com.spring.shop.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.Entity.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	/*
	 * 회원가입
	 * */
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	/*
	 * 회원조회
	 * */
	public Member find(Long Id) {
		return em.find(Member.class, Id);
	}

}
