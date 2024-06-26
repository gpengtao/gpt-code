package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

/**
 * @author pengtao.geng on 2019/11/7 6:51 下午
 */
public class StreamTest2 {

	@Test
	public void test_map_reduce() {
		// 设置线程池的大小
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "30");
		// 看看线程池多大
		ForkJoinPool pool = ForkJoinPool.commonPool();
		System.out.println(pool);

		// 要处理的数据
		List<Integer> list = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}

		long start = System.currentTimeMillis();

		// 处理
		List<MyScore> myScores = list.parallelStream()
				.map(item -> {
					// 假装高io，很耗时的操作
					sleep();

					// 返回结果
					MyScore myScore1 = new MyScore("111", item * 2 / 2);
					MyScore myScore2 = new MyScore("222", item * 3 / 3);
					return Lists.newArrayList(myScore1, myScore2);
				})
				.reduce((aList, bList) -> {
					System.out.println(Thread.currentThread().getName() + " 处理 " + Integer.toHexString(System.identityHashCode(aList)) + " " + Integer.toHexString(System.identityHashCode(bList)));

					for (MyScore bScore : bList) {
						MyScore sameTagScore = aList.stream()
								.filter(score -> Objects.equals(score.getTag(), bScore.getTag()))
								.findFirst()
								.orElse(null);
						if (sameTagScore != null) {
							sameTagScore.setScore(sameTagScore.getScore() + bScore.getScore());
						} else {
							aList.add(bScore);
						}
					}

					return aList;
				})
				.orElse(Lists.newArrayList());

		// 打印结果
		System.out.println("结果：" + Integer.toHexString(System.identityHashCode(myScores)));
		for (MyScore myScore : myScores) {
			System.out.println(Integer.toHexString(System.identityHashCode(myScore)) + " " + myScore);
		}

		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Data
	@Builder
	@NoArgsConstructor
	public static class MyScore {

		private String tag;

		private Integer score;

		public MyScore(String tag, Integer score) {
			this.tag = tag;
			this.score = score;
		}
	}

}