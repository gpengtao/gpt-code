package com.gpengtao.java.number;

import org.junit.Test;

/**
 * @author pengtao.geng on 2019-06-18 23:07
 */
public class TestFloat {

	@Test
	public void test() {
		float a = 1.0f / 0.0f;
		float b = Float.POSITIVE_INFINITY;
		System.out.println(a == b);


		System.out.println(0.0f / 0.0f);
	}

	/**
	 * 用一个循环相加 2000 万个 1.0f，最终的结果会是 1600 万左右，而不是 2000 万。
	 */
	@Test
	public void test_sum() {
		float sum = 0.0f;
		for (int i = 0; i < 20000000; i++) {
			float x = 1.0f;
			sum += x;
		}
		System.out.println("sum is " + sum);
	}
}
