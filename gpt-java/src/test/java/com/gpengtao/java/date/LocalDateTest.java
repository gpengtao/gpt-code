package com.gpengtao.java.date;

import com.google.common.collect.Lists;
import com.gpengtao.java.lambda.ToMapTest;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2020/4/13 1:51 下午
 */
public class LocalDateTest {

	@Test
	public void test() {
		LocalDate from = LocalDate.parse("2020-02-02");
		LocalDate end = LocalDate.parse("2020-05-01");

		long days = ChronoUnit.DAYS.between(from, end);
		System.out.println(days);
	}

	@Test
	public void test_1() {
		System.out.println(System.identityHashCode(new ToMapTest.Person()));
		System.out.println(System.identityHashCode(new ToMapTest.Person()));
	}

	@Test
	public void test_2() {
		ToMapTest.Person person = new ToMapTest.Person("11", 3, 7.728547);

		ArrayList<ToMapTest.Person> people = Lists.newArrayList(person, person, person, person);

		Map<String, ToMapTest.Person> map = people.stream().collect(Collectors.toMap(x -> x.getName(), v -> v, (a, b) -> {
			a.setMoney(a.getMoney() + b.getMoney());
			return a;
		}));

		System.out.println(map);
	}

	@Test
	public void test_util() {
		System.out.println(toLocalDateTime(new Date()));

		System.out.println(toLocalDate(new Date()));
		System.out.println(toLocalTime(new Date()));

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
	}

	private static LocalDateTime toLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	private static LocalDate toLocalDate(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date);
		return localDateTime.toLocalDate();
	}

	private static LocalTime toLocalTime(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date);
		return localDateTime.toLocalTime();
	}


}
