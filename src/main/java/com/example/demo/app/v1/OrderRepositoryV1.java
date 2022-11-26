package com.example.demo.app.v1;

import org.springframework.stereotype.Repository;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

	private final HelloTraceV1 traceV1;

	public void save(String itemId) {

		TraceStatus status = null;
		try {
			status = traceV1.begin("OrderRepository.save()");

			// 저장로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("에러 발생!");
			}
			sleep(1000);

			traceV1.end(status);
		} catch (Exception e) {
			traceV1.exception(status, e);
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
