package com.example.demo.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trace.callback.TraceCallback;
import com.example.demo.trace.callback.TraceTemplate;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@RestController
public class OrderControllerV5 {
	private final OrderServiceV5 orderServiceV5;
	private final TraceTemplate template;

	public OrderControllerV5(OrderServiceV5 orderServiceV5, LogTrace trace) {
		this.orderServiceV5 = orderServiceV5;
		this.template = new TraceTemplate(trace);
	}

	@GetMapping("/v5/request")
	public String request(String itemId) {
		return template.excute("OrderController.request()", () -> {
			orderServiceV5.orderItem(itemId);
			return "ok";
		});
	}
}
