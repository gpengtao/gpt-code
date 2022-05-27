package com.gpengtao.java.jvm.clazz;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author pengtao.geng on 2020/7/24 6:19 下午
 */
public class MethodTest {

	/**
	 * jvm不保证 getDeclaredMethods 返回的方法的顺序
	 */
	@Test
	public void test_method_sort() {
		Method[] declaredMethods = XXXClass.class.getDeclaredMethods();

		for (Method method : declaredMethods) {
			System.out.println(method);
		}
	}

	public static class XXXClass {
		public void getName1() {
		}

		public void getName2() {
		}

		public void getName3() {
		}

		public void getName4() {
		}

		public void getName5() {
		}
	}
}
