package com.gpengtao.java.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author pengtao.geng on 2023/8/30 14:31.
 */
public class ScheduledExecutorTest {

	/**
	 * run执行结束之后开始计时，到了delay时间才进行下一次run
	 */
	@Test
	public void test_after_run() throws IOException {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleWithFixedDelay(
				() -> {
					System.out.println("run");
					sleep(5);
					System.out.println("sleep over");
				},
				0,
				3,
				TimeUnit.SECONDS);

		System.in.read();
	}

	private static void sleep(int second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
