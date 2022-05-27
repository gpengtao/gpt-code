package com.gpengtao.java.number;

import org.junit.Test;

import java.util.Objects;

/**
 * @author pengtao.geng on 2019-05-21 11:24
 */
public class TestInteger {

	@Test
	public void test() {
	    // false
		System.out.println(new Integer(1) == new Integer(1));

		// true
		System.out.println(new Integer(1) == 1);

		// true
        System.out.println(Objects.equals(new Integer("12345"), new Integer("12345")));

        // true,int 12345被隐式装箱
        System.out.println(Objects.equals(12345, new Integer("12345")));

		// npe
		System.out.println(getValue() == getValue2());
	}

	private int getValue() {
		return 1;
	}

	private Integer getValue2() {
		return null;
	}

	@Test
	public void test_() {
		int num = Integer.MAX_VALUE;

		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
