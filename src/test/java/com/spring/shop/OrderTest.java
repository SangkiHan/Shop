package com.spring.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
		
//		Member member = new Member();
//		member.setUsername("한상기3");
//		member.setAddress(new Address("수원시", "하률로", "16324"));
//		
//		memberService.join(member);
//		
//		Item item = new Book();
//		item.setPrice(10000);
//		item.setName("JPA2");
//		item.setStockQuantity(100);
//		
//		itemService.save(item);
		
//		List<Long> itemId = new ArrayList<>();
//		itemId.add(2L);
//		itemId.add(7L);
//		orderService.order(6L, itemId, 2);
	}
	
//	@org.junit.jupiter.api.Test
//	@Rollback(false)
	void 취소테스트(){
		
		orderService.cancel(3L);
	}
}
