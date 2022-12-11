package com.example.demo.app.v3;

import org.springframework.stereotype.Service;

import com.example.demo.trace.TraceId;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV2;
import com.example.demo.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepositoryV3;
	private final LogTrace trace;

	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.orderItem()");
			orderRepositoryV3.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}
}
