package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2024/4/10 17:35.
 */
public class StreamMappingTest {

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class PersonScoreRecord {
		private String name;
		private Integer age;
		private Score score;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Score {

		private Integer score;
		private String mark;


	}

	private static Score initScore() {
		return new Score(0, "from init");
	}

	private static Score sumToLeft(Score left, Score right) {
		left.setScore(left.getScore() + right.getScore());
		return left;
	}

	private static Score sumToRight(Score a, Score b) {
		b.setScore(a.getScore() + b.getScore());
		return b;
	}

	private static Score sumToNew(Score a, Score b) {
		int sum = a.getScore() + b.getScore();
		return new Score(sum, "from sum");
	}

	@Test
	public void test_groupBy_mappingReducing() {
		// 此写法错误，原因：reducing的默认值只会执行一次，后续会用于各个分组的运算
		List<PersonScoreRecord> records2 = buildRecords();
		Map<String, Score> result2 = records2.stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								PersonScoreRecord::getScore,
								Collectors.reducing(initScore(), StreamMappingTest::sumToRight)
						)));
		System.out.println("result1，错误: " + result2);

		// ok
		Map<String, Integer> result3 = buildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								record -> record.getScore().getScore(),
								Collectors.reducing(0, Integer::sum)
						)));
		System.out.println("result2，对的: " + result3);

		// ok
		Map<String, Score> result1 = buildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.collectingAndThen(
								Collectors.toList(),
								list -> list.stream()
										.map(PersonScoreRecord::getScore)
										.reduce(initScore(), StreamMappingTest::sumToRight)
						)));
		System.out.println("result3，对的: " + result1);
	}

	private static List<PersonScoreRecord> buildRecords() {
		PersonScoreRecord record1 = new PersonScoreRecord();
		record1.setName("a");
		record1.setScore(new Score(10, "from a1"));

		PersonScoreRecord record2 = new PersonScoreRecord();
		record2.setName("a");
		record2.setScore(new Score(10, "from a2"));

		PersonScoreRecord record3 = new PersonScoreRecord();
		record3.setName("b");
		record3.setScore(new Score(10, "from b"));

		return Lists.newArrayList(record1, record2, record3);
	}
}
