package com.spring.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.shop.controller.dto.OrderSearch;
import com.spring.shop.controller.dto.OrderSimpleQueryDto;
import com.spring.shop.entity.Order;

@Repository
public class OrderRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long save(Order order) {
		em.persist(order);
		return order.getId();
	}
	
	public Order findOne(Long orderId) {
		return em.createQuery("select o from Order o where o.Id = :orderId", Order.class)
				.setParameter("orderId", orderId)
				.getSingleResult();
	}
	
	public List<Order> findAll(OrderSearch orderSearch){
		return em.createQuery("select o from Order o join o.member m"+
						"where o.status = :status"+
						"and m.name like :name", Order.class)
						.setParameter("status", orderSearch.getOrderStatus())
						.setParameter("name", orderSearch.getMemberName())
						.setMaxResults(1000)
						.getResultList();
	}
	
	public List<Order> findAllByString(OrderSearch orderSearch){
		return em.createQuery("select o from Order o join o.member m"+
						" where o.status = :status"+
						" and m.username like :name", Order.class)
						.setParameter("status", orderSearch.getOrderStatus())
						.setParameter("name", orderSearch.getMemberName())
						.setMaxResults(1000)
						.getResultList();
	}
	
	public List<Order> findAllWithMemberDelivey(OrderSearch orderSearch){
		return em.createQuery("select o from Order o"+
						" join fetch o.member m"+
						" join fetch o.delivery d", Order.class)
				.getResultList();
									
	}
	
	public List<OrderSimpleQueryDto> findOrderDtos(OrderSearch orderSearch){
		return em.createQuery("select new com.spring.shop.controller.dto.OrderSimpleQueryDto(o.id, m.username, o.orderDate, o.status, d.address)"+
							" from Order o"+
							" join o.member m"+
							" join o.delivery d", OrderSimpleQueryDto.class)
					.getResultList();
	}
	
	/*
	 * distinct를 사용하면 일대다 일시 중복되는 것을 JPA가 걸러준다.
	 * 단점은 페이징처리가 불가하다. 이유 : JPA에서 모든데이터를 DB에서 읽어오고 메모리에서 페이징을 해버려서 위험하다.
	 * */
	public List<Order> findAllWithItem(OrderSearch orderSearch){
		return em.createQuery("select distinct o from Order o"+
							" join fetch o.member m"+
							" join fetch o.delivery d"+
							" join fetch o.orderItems oi"+
							" join fetch oi.item i", Order.class)
					.getResultList();
	}
}
