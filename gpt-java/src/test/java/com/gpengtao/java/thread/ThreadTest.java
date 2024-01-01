package com.gpengtao.java.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author pengtao.geng on 2024/1/1 22:05.
 */
public class ThreadTest {

	@Test
	public void test_implThread1_extends_thread() {
		new MyThread().start();
	}

	@Test
	public void test_implThread2_thread_use_run() {
		new Thread(() -> System.out.println("hello")).start();
	}

	@Test
	public void test_implThread3_thread_use_future_task() throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "hello";
			}
		});
		new Thread(futureTask).start();
		String ret = futureTask.get();
		System.out.println(ret);
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("my thread");
		}
	}
}
