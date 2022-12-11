package com.example.demo.app.v3;

import org.springframework.stereotype.Repository;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

	private final LogTrace trace;

	public void save(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderRepository.save()");

			// 저장로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("에러 발생!");
			}
			sleep(1000);

			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
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
