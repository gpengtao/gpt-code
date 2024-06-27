package com.gpengtao.java.thread2;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pengtao.geng on 14-10-20.
 */
public class ScheduledFutureTest {

	@SneakyThrows
	@Test
	public void test_schedule_future() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		ScheduledFuture<Object> future = executor.schedule(
				() -> {
					System.out.println("run");
					return "hello world";
				},
				10,
				TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " 是否取消:" + future.isCancelled() + " 是否done:" + future.isDone());
			Thread.sleep(500);
		}

		Object ret = future.get();
		System.out.println("执行结果是:" + ret);

		System.in.read();
	}
}
