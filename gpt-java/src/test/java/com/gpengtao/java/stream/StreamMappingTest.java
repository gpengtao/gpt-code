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

		public static Score sum(Score a, Score b) {
			a.setScore(a.getScore() + b.getScore());
			return a;
		}

		public static Score init() {
			return new Score(0);
		}
	}


	/**
	 * 测试stream
	 */
	@Test
	public void test_groupBy_mappingReducing() {
		// ok
		Map<String, Score> result1 = buildRecords().stream()
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
		Map<String, Score> result2 = buildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								PersonScoreRecord::getScore,
								Collectors.reducing(Score.init(), Score::sum)
						)));
		System.out.println("result2，错误: " + result2);

		// ok
		Map<String, Integer> result3 = buildRecords().stream()
				.collect(Collectors.groupingBy(
						PersonScoreRecord::getName,
						Collectors.mapping(
								record -> record.getScore().getScore(),
								Collectors.reducing(0, Integer::sum)
						)));
		System.out.println("result3，对的: " + result3);
	}

	private static List<PersonScoreRecord> buildRecords() {
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
}
