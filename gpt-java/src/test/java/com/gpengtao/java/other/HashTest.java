package com.gpengtao.java.other;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author pengtao.geng on 2019-06-26 21:37
 */
public class HashTest {

	public static class Hello {

		private String name;

		private int age;

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
			Hello hello = (Hello) o;
			return age == hello.age &&
					Objects.equals(name, hello.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, age);
		}
	}

	@Test
	public void test_not_can_change_hash_map_key() {

		Hello hello = new Hello();
		hello.setName("gpt");
		hello.setAge(20);

		HashMap<Hello, String> map = Maps.newHashMap();
		map.put(hello, "world");

		System.out.println(map.get(hello));

		hello.setAge(30);
		System.out.println(map.get(hello));
	}
}
