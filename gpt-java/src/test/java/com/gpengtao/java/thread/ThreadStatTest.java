package com.gpengtao.java.thread;

import org.junit.Test;

/**
 * @author pengtao.geng on 2018/11/29 12:37 PM.
 */
public class ThreadStatTest {

	@Test
	public void test_thread_state() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("============ I'm ok ============");
				}
			}
		};
		thread.start();

		for (int i = 0; i < 100; i++) {
			System.out.println(thread.getState());
		}
	}

	private static void sleepSome(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
