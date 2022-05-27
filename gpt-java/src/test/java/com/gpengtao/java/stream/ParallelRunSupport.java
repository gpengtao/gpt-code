package com.gpengtao.java.stream;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author pengtao.geng on 2020/9/9 5:50 下午
 */
public class ParallelRunSupport {

	private static final ForkJoinPool POOL = new ForkJoinPool(2);

	@SneakyThrows
	public static void run(Callable<Object> callable) {
		ForkJoinTask<Object> task = POOL.submit(callable);
		System.out.println("已经提任务：" + callable);
		task.get();
		System.out.println("任务已执行完毕：" + callable);
	}
}
