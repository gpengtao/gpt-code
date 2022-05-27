package com.gpengtao.java.string;

import org.junit.Test;

/**
 * @author pengtao.geng on 2018/8/17 下午3:18.
 */
public class TestString {

	@Test
	public void test_string() {
		String str2 = new String("aa");
		str2.intern();

		String str1 = "aa";

		System.out.println(str1 == str2);
	}

	@Test
	public void test_string2() {
		String str2 = new String("aa") + new String("bb");
		str2.intern();

		String str1 = "aabb";

		System.out.println(str1 == str2);
	}
}
