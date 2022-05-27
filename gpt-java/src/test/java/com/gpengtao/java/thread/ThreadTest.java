package com.gpengtao.java.thread;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pengtao.geng on 2018/7/17 下午6:39.
 */
public class ThreadTest {

	@Test
	public void test_throw_oom_exception() throws IOException {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000));

		for (int i = 0; i < 10; i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread() + " state: " + Thread.currentThread().getState());
					List<Object> list = Lists.newArrayList();
					while (true) {
						long[] memory = new long[8 * 1024 * 1024];
						list.add(memory);
					}
				}
			});
		}

		System.out.println("main end");

		System.in.read();
	}

	@Test
	public void test_thread_init_size() throws IOException {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), r -> {
			System.out.println("new thread");

			Thread thread = new Thread(r);
			thread.setName("gpt");
			return thread;
		});

		// 提前准备好所有的线程
		pool.prestartAllCoreThreads();

		pool.submit(() -> System.out.println("hello1"));
		pool.submit(() -> System.out.println("hello2"));
		pool.submit(() -> System.out.println("hello3"));

		System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

		int read = System.in.read();

	}

	@Test
	public void test_schedule_pool_stack() throws IOException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		// 不打日志，线程死掉
		AtomicInteger integer = new AtomicInteger(0);
		executor.scheduleAtFixedRate(() -> {

			System.out.println(integer.incrementAndGet());

			if (integer.intValue() == 3) {
				throw new RuntimeException();
			}
		}, 0, 1, TimeUnit.SECONDS);

		System.in.read();
	}

	@Test
	public void test_fix_pool_stack() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		AtomicInteger integer = new AtomicInteger(0);

		for (int i = 0; i < 10; i++) {
			executor.execute(() -> {
				System.out.println(integer.incrementAndGet());

				if (integer.intValue() == 3) {
					System.out.println("error" + Thread.currentThread());
					throw new RuntimeException();
				}
			});
		}

		Thread.sleep(10000);
	}

	@Test
	public void test_thread_daemon() {
		Thread thread = new Thread();
		thread.setDaemon(true);

		System.out.println(thread.isDaemon());

		System.out.println(Thread.currentThread().isDaemon());
	}
}
