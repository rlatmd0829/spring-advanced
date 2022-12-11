package com.example.demo.app.v2;

import org.springframework.stereotype.Service;

import com.example.demo.trace.TraceId;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;
import com.example.demo.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepositoryV2;
	private final HelloTraceV2 traceV2;

	public void orderItem(TraceId traceId, String itemId) {
		TraceStatus status = null;
		try {
			status = traceV2.beginSync(traceId,"OrderService.orderItem()");
			orderRepositoryV2.save(status.getTraceId(), itemId);
			traceV2.end(status);
		} catch (Exception e) {
			traceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}
}
