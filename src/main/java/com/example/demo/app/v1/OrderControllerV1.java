package com.example.demo.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

	private final OrderServiceV1 orderServiceV1;
	private final HelloTraceV1 traceV1;

	@GetMapping("/v1/request")
	public String request(String itemId) {
		TraceStatus status = null;
		try {
			status = traceV1.begin("OrderController.request()");
			orderServiceV1.orderItem(itemId);
			traceV1.end(status);
			return "ok";
		} catch (Exception e) {
			traceV1.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}
}
