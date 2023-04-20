package com.spring.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Member;

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
	
	/*
	 * 회원리스트조회
	 * */
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
	/*
	 * 회원조회 by username
	 * */
	public List<Member> findByUsername(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
				.setParameter("username", username)
				.getResultList();
	}
}
