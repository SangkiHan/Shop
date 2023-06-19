package com.spring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.spring.shop.entity.Address;
import com.spring.shop.entity.Member;
import com.spring.shop.entity.item.Book;
import com.spring.shop.entity.item.Item;
import com.spring.shop.service.ItemService;
import com.spring.shop.service.MemberService;
import com.spring.shop.service.OrderService;

@SpringBootTest
public class OrderTest {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
	
	@org.junit.jupiter.api.Test
	@Rollback(false)
	void 주문테스트(){
		
		Member member = new Member();
		member.setUsername("한상기");
		member.setAddress(new Address("수원시", "하률로", "16324"));
		
		memberService.join(member);
		
		Item item = new Book();
		item.setPrice(10000);
		item.setName("JPA");
		item.setStockQuantity(100);
		
		itemService.save(item);
		
		orderService.order(1L, 2L, 2);
	}
	
//	@org.junit.jupiter.api.Test
//	@Rollback(false)
	void 취소테스트(){
		
		orderService.cancel(3L);
	}
}
