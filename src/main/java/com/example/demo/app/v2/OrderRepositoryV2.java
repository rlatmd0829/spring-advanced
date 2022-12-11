package com.example.demo.app.v2;

import org.springframework.stereotype.Repository;

import com.example.demo.trace.TraceId;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;
import com.example.demo.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

	private final HelloTraceV2 traceV2;

	public void save(TraceId traceId, String itemId) {

		TraceStatus status = null;
		try {
			status = traceV2.beginSync(traceId, "OrderRepository.save()");

			// 저장로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("에러 발생!");
			}
			sleep(1000);

			traceV2.end(status);
		} catch (Exception e) {
			traceV2.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
