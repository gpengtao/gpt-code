package com.gpengtao.java.lang;

import org.junit.Test;

/**
 * @author pengtao.geng on 2020/6/2 9:55 下午
 */
public class CloneTest {

	public static class Person {

	}

	@Test
	public void test_clone() {
		Person[] people1 = new Person[]{new Person(), new Person(), new Person()};
		System.out.println(people1);
		for (Person person : people1) {
			System.out.println(person);
		}

		// clone 之后数组是新的，元素还是旧的
		Person[] people2 = people1.clone();
		System.out.println(people2);
		for (Person person : people2) {
			System.out.println(person);
		}
	}
}
