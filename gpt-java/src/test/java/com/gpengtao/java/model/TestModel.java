package com.gpengtao.java.model;

import org.junit.Test;

/**
 * @author pengtao.geng on 2018/8/17 上午10:23.
 */
public class TestModel {

	@Test
	public void test() throws IllegalAccessException, InstantiationException {
		A a = A.class.newInstance();
		B b = B.class.newInstance();

		a.setB(b);

		System.out.println(a);
		System.out.println(b);
	}
}
