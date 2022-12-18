package com.example.demo.app.v5;

import org.springframework.stereotype.Repository;

import com.example.demo.trace.callback.TraceTemplate;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate template;

	public OrderRepositoryV5(LogTrace trace) {
		this.template = new TraceTemplate(trace);
	}

	public void save(String itemId) {
		template.excute("OrderRepository.save()", () -> {
			// 저장로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("에러 발생!");
			}
			sleep(1000);
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
