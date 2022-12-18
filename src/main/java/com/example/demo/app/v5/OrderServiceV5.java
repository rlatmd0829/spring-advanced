package com.example.demo.app.v5;

import org.springframework.stereotype.Service;

import com.example.demo.trace.callback.TraceTemplate;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepositoryV5;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepositoryV5, LogTrace trace) {
		this.orderRepositoryV5 = orderRepositoryV5;
		this.template = new TraceTemplate(trace);
	}

	public void orderItem(String itemId) {
		template.excute("OrderService.orderItem()", () -> {
			orderRepositoryV5.save(itemId);
			return null;
		});
	}
}
