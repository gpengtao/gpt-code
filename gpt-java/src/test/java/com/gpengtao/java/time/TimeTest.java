package com.gpengtao.java.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

/**
 * @author pengtao.geng on 2019-08-23 16：57
 */
public class TimeTest {

	/**
	 * 时间跨天
	 */
	@Test
	public void test_local_time_over_date() {
		LocalTime localTime = LocalTime.of(22, 30, 0);

		System.out.println(localTime);

		// 实际跨天了，第二天2：30
		System.out.println(localTime.plusHours(4));
	}

	/**
	 * period，日期差，注意：年月日一起才是总的日期差
	 */
	@Test
	public void test_period() {
		Period period = Period.between(LocalDate.of(2018, 7, 1), LocalDate.of(2019, 8, 28));

		System.out.println("toString：" + period);
		System.out.println("支持的时间单位：" + period.getUnits());

		System.out.println("差多少年：" + period.getYears());
		System.out.println("差多少月：" + period.getMonths());
		System.out.println("差多少天：" + period.getDays());
	}

	/**
	 * duration，时间差
	 */
	@Test
	public void test_duration() {
		Duration duration = Duration.between(LocalDateTime.parse("2019-08-27T09:30:00"), LocalDateTime.parse("2019-08-28T10:59:59"));

		System.out.println("toString：" + duration);
		System.out.println("支持的时间单位：" + duration.getUnits());

		System.out.println("差了多少秒：" + duration.getSeconds());
		System.out.println("差了多少纳秒：" + duration.getNano());

		System.out.println("转化成小时：" + duration.toHours());
		System.out.println("转化成分钟：" + duration.toMinutes());
	}

	/**
	 * 调整
	 */
	@Test
	public void test_adjust() {
		LocalDate localDate = LocalDate.of(2019, 8, 28);

		// 和2019-08-28同一个星期的 星期一
		System.out.println(localDate.with(DayOfWeek.MONDAY));

		// 和2019-08-28同一个星期的 星期一
		System.out.println(localDate.with(DayOfWeek.WEDNESDAY));
	}
}
