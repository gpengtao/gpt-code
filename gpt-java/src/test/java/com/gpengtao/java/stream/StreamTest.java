package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2018/9/17 下午4:18.
 */
public class StreamTest {

	@Test
	public void test_all_match() {
		List<Integer> list = Lists.newArrayList();

		boolean allMatch = list.stream()
				.filter(Objects::nonNull)
				.allMatch(i -> i > 100);

		System.out.println(allMatch);
	}

	@Test
	public void test_limit() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

		List<Integer> limit = list.stream().limit(10).collect(Collectors.toList());

		System.out.println(limit);
	}

	@Test
	public void test_limit_2() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Integer> limitList = list.stream()
				.map(i -> {
					System.out.println("map1111:" + i);
					return i;
				})
				.filter(i -> {
					System.out.println("filter:" + i);
					return i > 3;
				})
				.map(i -> {
					System.out.println("map:" + i);
					return i * 100;
				})
				.limit(2)
				.collect(Collectors.toList());

		System.out.println(limitList);
	}

	@Test
	public void test_group_by() {
		Person person1 = new Person("11", 33);
		Person person2 = new Person("22", 33);

		Map<Person, List<Person>> group = Lists.newArrayList(person1, person2).stream()
				.collect(Collectors.groupingBy(x -> x));

		group.forEach((key, value) -> {
			System.out.println(key + " ===> " + value);
		});
	}

	public static class Person {
		private String name;
		private int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Person person = (Person) o;
			return age == person.age;
		}

		@Override
		public int hashCode() {
			return Objects.hash(age);
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
}
