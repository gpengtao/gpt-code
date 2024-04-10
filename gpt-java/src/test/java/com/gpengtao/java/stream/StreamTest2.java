package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import com.gpengtao.java.stream.model.PersonScoreRecord;
import com.gpengtao.java.stream.model.Score;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2019/11/7 6:51 下午
 */
public class StreamTest2 {

	/**
	 * 测试stream
	 */
	@Test
	public void test_groupBy_mappingReducing() {
		// ok
		Map<String, Score> result1 = getBuildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.collectingAndThen(
								Collectors.toList(),
								list -> list.stream()
										.map(PersonScoreRecord::getScore)
										.reduce(Score.init(), Score::sum)
						)));
		System.out.println("result1，对的: " + result1);

		// 此写法错误
		Map<String, Score> result2 = getBuildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								PersonScoreRecord::getScore,
								Collectors.reducing(Score.init(), Score::sum)
						)));
		System.out.println("result2，错误: " + result2);

		// ok
		Map<String, Integer> result3 = getBuildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								record -> record.getScore().getScore(),
								Collectors.reducing(0, Integer::sum)
						)));
		System.out.println("result3，对的: " + result3);
	}

	private static List<PersonScoreRecord> getBuildRecords() {
		PersonScoreRecord record1 = new PersonScoreRecord();
		record1.setName("a");
		record1.setScore(new Score(10));

		PersonScoreRecord record2 = new PersonScoreRecord();
		record2.setName("a");
		record2.setScore(new Score(10));

		PersonScoreRecord record3 = new PersonScoreRecord();
		record3.setName("b");
		record3.setScore(new Score(10));

		return Lists.newArrayList(record1, record2, record3);
	}

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