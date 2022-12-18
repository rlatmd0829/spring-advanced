package com.example.demo.app.v4;

import org.springframework.stereotype.Repository;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

	private final LogTrace trace;

	public void save(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				// 저장로직
				if (itemId.equals("ex")) {
					throw new IllegalStateException("에러 발생!");
				}
				sleep(1000);
				return null;
			}
		};
		template.execute("OrderRepository.save()");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
