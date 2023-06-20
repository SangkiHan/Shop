package com.spring.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final EntityManager em;
	
	public void join(Member member) {
		if(member.getId()==0) {
			em.persist(member);
		}
		else {
			em.merge(member);
		}
	}
	
	public Member selectOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> selectMemberList(){
		return em.createQuery(
				"select m"+
				" from Member m", Member.class)
			.getResultList();
	}
}
