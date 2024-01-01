package com.gpengtao.java.thread;

import org.junit.Test;

/**
 * @author pengtao.geng on 2024/1/1 22:05.
 */
public class ThreadTest {

	@Test
	public void test_impl_thread_1() {
		new MyThread().start();
	}

	@Test
	public void test_impl_thread_2() {
		new Thread(() -> System.out.println("hello")).start();
	}

	@Test
	public void test_impl(){
		Thread thread = new Thread();
		thread.start();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("my thread");
		}
	}
}
