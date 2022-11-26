package com.example.demo.app.v1;

import org.springframework.stereotype.Service;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

	private final OrderRepositoryV1 orderRepositoryV1;
	private final HelloTraceV1 traceV1;

	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = traceV1.begin("OrderService.orderItem()");
			orderRepositoryV1.save(itemId);
			traceV1.end(status);
		} catch (Exception e) {
			traceV1.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}
}
