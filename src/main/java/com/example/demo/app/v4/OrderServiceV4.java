package com.example.demo.app.v4;

import org.springframework.stereotype.Service;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepositoryV4;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				orderRepositoryV4.save(itemId);
				return null;
			}
		};
		template.execute("OrderService.orderItem()");
	}
}
