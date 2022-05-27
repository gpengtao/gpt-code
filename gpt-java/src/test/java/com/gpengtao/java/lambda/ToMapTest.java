package com.gpengtao.java.lambda;

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
 * @author pengtao.geng on 2020/4/8 10:28 下午
 */
public class ToMapTest {

	@Test
	public void test() {
		List<Person> list = Lists.newArrayList(
				Person.builder()
						.name("gpt")
						.build()
		);

		Map<String, Integer> map = list.stream().collect(
				Collectors.toMap(
						Person::getName,
						Person::getAge
				));
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Person {
		private String name;
		private Integer age;
		private double money;
	}
}
