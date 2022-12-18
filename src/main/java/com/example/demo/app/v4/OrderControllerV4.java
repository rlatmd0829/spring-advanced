package com.example.demo.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
	private final OrderServiceV4 orderServiceV4;
	private final LogTrace trace;

	@GetMapping("/v4/request")
	public String request(String itemId) {

		AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
			@Override
			protected String call() {
				orderServiceV4.orderItem(itemId);
				return "ok";
			}
		};
		return template.execute("OrderController.request()");
	}
}
