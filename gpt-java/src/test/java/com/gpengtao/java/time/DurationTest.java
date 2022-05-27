package com.gpengtao.java.time;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by pengtao.geng on 2021/11/14 1:07 下午.
 */
public class DurationTest {

	@Test
	public void test() {
		long seconds1 = Duration.between(LocalDateTime.parse("2021-11-14T12:58:06"), LocalDateTime.parse("2021-11-14T13:06:11")).getSeconds();
		long seconds2 = Duration.between(LocalDateTime.parse("2021-11-14T12:42:14"), LocalDateTime.parse("2021-11-14T12:51:10")).getSeconds();
		System.out.println(seconds1);
		System.out.println(seconds2);

		System.out.println(1.0 * (seconds2 - seconds1) / seconds2 * 100);
	}

	@Test
	public void test_duration(){
		long seconds = Duration.between(LocalDateTime.parse("2021-11-25T16:21:51"), LocalDateTime.parse("2021-11-25T16:23:10")).getSeconds();

		System.out.println(seconds);

		System.out.println(seconds / 5.0);
	}
}
