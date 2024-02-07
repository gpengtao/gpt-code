package com.gpengtao.utils;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2020/9/9 5:50 下午
 */
public class ParallelRunSupport {

	private static final ForkJoinPool POOL = new ForkJoinPool(8);

	@SneakyThrows
	public static <T> Map<String, T> run(List<String> items, boolean parallel, Function<String, T> f) {
		// 并行执行
		if (parallel) {
			ForkJoinTask<Map<String, T>> task = POOL.submit(
					() -> items.parallelStream()
							.collect(Collectors.toMap(Function.identity(), f)));
			return task.get();
		} else {
			return items.stream()
					.collect(Collectors.toMap(Function.identity(), f));
		}
	}

	@SneakyThrows
	public static <T, R> List<R> run(List<T> list, Function<T, R> f) {
		ForkJoinTask<List<R>> task = POOL.submit(() -> list.parallelStream().map(f).collect(Collectors.toList()));
		return task.get();
	}

	@SneakyThrows
	public static <T> T run(Callable<T> callable) {
		ForkJoinTask<T> task = POOL.submit(callable);
		return task.get();
	}
}
