package com.example.demo.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.app.v1.OrderServiceV1;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;
import com.example.demo.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

	private final OrderServiceV2 orderServiceV2;
	private final HelloTraceV2 traceV2;

	@GetMapping("/v2/request")
	public String request(String itemId) {
		TraceStatus status = null;
		try {
			status = traceV2.begin("OrderController.request()");
			orderServiceV2.orderItem(status.getTraceId(), itemId);
			traceV2.end(status);
			return "ok";
		} catch (Exception e) {
			traceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}
}
