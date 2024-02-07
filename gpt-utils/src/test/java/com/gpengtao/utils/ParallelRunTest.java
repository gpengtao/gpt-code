package com.gpengtao.utils;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author pengtao.geng on 2024/2/7 11:22.
 */
public class ParallelRunTest {

	@Test
	public void test_run() {
		List<Integer> list = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
		List<String> ret = ParallelRunSupport.run(list, integer -> {
			int millis = Math.abs(new Random(integer).nextInt(1000));
			System.out.println(Thread.currentThread() + " run, sleep " + millis);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "hello-" + integer;
		});
		System.out.println(ret);
	}
}
