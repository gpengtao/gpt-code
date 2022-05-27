package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author pengtao.geng on 2020/9/9 10:51 上午
 */
public class ParallelStreamTest {

	@Test
	public void test_parallel() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

		list.parallelStream().forEach(num -> {
			System.out.println(Thread.currentThread().getName() + ":" + num * 2);
		});

		ForkJoinPool customForkJoinPool = new ForkJoinPool(4);
		customForkJoinPool.submit(() ->
				list.parallelStream().forEach(num -> {
					System.out.println(Thread.currentThread().getName() + ":" + num * 2);
				})
		);
	}

	@Test
	public void test_support() throws IOException {

		List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());

		new Thread(() ->
				ParallelRunSupport.run(() -> {
					list.parallelStream().forEach(num -> {
						System.out.println("1111111 " + Thread.currentThread().getName() + " 正在运行，处理：" + num);
						sleep();
					});
					return null;
				})
		).start();

		new Thread(() ->
				ParallelRunSupport.run(() -> {
					list.parallelStream().forEach(num -> {
						System.out.println("2222222 " + Thread.currentThread().getName() + " 正在运行，处理：" + num);
						sleep();
					});
					return null;
				})
		).start();

		System.out.println("main");
		System.in.read();
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_fork_join() {
		ForkJoinPool pool = new ForkJoinPool(4);

		System.out.println(pool);
	}

	@Test
	public void test_flat_map() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Map<Integer, List<Integer>> map = list.parallelStream()
				.flatMap(index -> Lists.newArrayList(1, 2, 3, 4, 5).stream())
				.collect(Collectors.groupingBy(Function.identity()));
		System.out.println(map);
	}
}
