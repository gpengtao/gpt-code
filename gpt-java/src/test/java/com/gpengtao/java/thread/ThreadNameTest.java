package com.gpengtao.java.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author pengtao.geng on 2022/6/2 15:58.
 */
public class ThreadNameTest {

	@Test
	public void test_set_thread_name() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);

		IntStream.rangeClosed(1, 10)
				.forEach(index -> {
					executorService.submit(() -> {
						System.out.println("当前名字：" + Thread.currentThread().getName());
						String oldName = Thread.currentThread().getName();
						Thread.currentThread().setName(oldName + "#####" + index);
						System.out.println("set后名字：" + Thread.currentThread().getName());

						Thread.currentThread().setName(oldName);
					});
				});
	}
}
