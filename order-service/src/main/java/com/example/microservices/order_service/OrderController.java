package com.example.microservices.order_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@GetMapping("/order-get")
	public String get() {
		return "Order-Hello-Wrold";
	}

	@GetMapping("/getorderbyid/{id}")
	public String get(@PathVariable("id") String id) {
		String orderValue = "Java:1";

		if (id.equals("2")) {
			orderValue = ".Net:2";
		}
		String response = orderValue;

		return response;
	}
}
