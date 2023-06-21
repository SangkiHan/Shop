package com.spring.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.controller.dto.OrderDto;
import com.spring.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/save")
	public void order(@RequestBody OrderDto.Request request) {
		orderService.save(request);
	}
	
	@PostMapping("/cancel")
	public void cancel(@RequestParam("orderId")Long orderId) {
		orderService.cancel(orderId);
	}
	
	@GetMapping("/selectList")
	public List<OrderDto.Info> selectList(@RequestParam("memberId")Long memberId){
		return orderService.selectList(memberId);
	}

}
