package com.gpengtao.java.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * @author pengtao.geng on 2019-03-27 00:21
 */
public class LimiterTest {

	@Test
	public void test_limiter() throws InterruptedException {
		RateLimiter limiter = RateLimiter.create(10);

		for (int i = 0; i < 10000; i++) {

			Thread.sleep(90);

			System.out.println(limiter.tryAcquire());

			if (i % 10 == 0) {
				System.out.println("===================");
			}
		}
	}
}
