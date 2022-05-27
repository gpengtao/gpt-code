package com.gpengtao.java.other;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.gpengtao.java.model.Person;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by pengtao.geng on 2017/10/24.
 */
public class Other {

	@Test
	public void test() {
		System.out.println(10.1 % 3);

		System.out.println(~1);
	}

	@Test
	public void test_list() {
		List<Person> list = new ArrayList<>(3);

		list.add(new Person("1"));
		list.add(new Person("2"));
		list.add(new Person("3"));
		list.add(new Person("4"));
		list.add(new Person("5"));

		for (int i = 0; i < 100; i++) {
			list.add(new Person("" + i));
		}

		Person person = list.get(0);
		list.remove(person);

		System.out.println(list);
	}

	@Test
	public void test_integer() {
		Integer integer1 = new Integer("1234567");
		Integer integer2 = new Integer("1234567");

		System.out.println(integer1 == integer2);
	}

	@Test
	public void test_integer_compare() {
		Integer integer1 = new Integer("1234567");
		int integer2 = 123456;

		System.out.println(integer1 > integer2);

		System.out.println(integer1.compareTo(integer2));

	}

	@Test
	public void test_double() {
		double a = 0.3;
		System.out.println(a);

		BigDecimal b = new BigDecimal(0.6);
		System.out.println(b);
	}

	@Test
	public void test_uniq() {
		List<Integer> list = Lists.newArrayList(1, 2, 1, 2, 3, 4, 1, 2);

		System.out.println(list);
	}

	@Test
	public void test_sort() {
		List<Integer> list = Lists.newArrayList(3, 2, 1, 5, 4);

		list.sort(Integer::compareTo);

		System.out.println(list);
	}

	@Test
	public void test_clone() throws CloneNotSupportedException {
		Man man = new Man("xxx", 100);
		man.setList(Lists.newArrayList("hello", "world"));

		Object clone = man.clone();

		System.out.println(clone);
	}

	@Test
	public void test_file_remove() throws IOException {
		List<String> all = Files.readLines(new File("/Users/pengtao.geng/11.txt"), Charset.defaultCharset());
		List<String> now = Files.readLines(new File("/Users/pengtao.geng/22.txt"), Charset.defaultCharset());

		all.removeAll(now);

		System.out.println(all.size());

		System.out.println(all);
	}

	@Test
	public void test_just() {

		int noChangeWin = 0;
		int changeWin = 0;
		for (int times = 0; times < 100000; times++) {
			// 中奖的序号
			int winIndex = getRandomInt();
			// 主持人掀开的序号
			int showIndex = getRandomInt();
			// 小明选择的序号
			int chooseIndex = getRandomInt();

			// 主持人知道哪个中奖，所以不会发生主持人选了那个中奖序号
			// 为了模拟这个场景，跳过组合的序号即可
			if (showIndex == winIndex || showIndex == chooseIndex) {
				continue;
			}

			if (chooseIndex == winIndex) {
				noChangeWin++;
			} else {
				changeWin++;
			}

			System.out.println("共计：" + (noChangeWin + noChangeWin) + "次，概率统计：" + noChangeWin / (float) (noChangeWin + changeWin) + "   " + changeWin / (float) (noChangeWin + changeWin));
		}
	}

	private int getRandomInt() {
		try {
			Thread.sleep(new Random(UUID.randomUUID().hashCode()).nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Math.abs(new Random(UUID.randomUUID().hashCode()).nextInt()) % 3;
	}

}
