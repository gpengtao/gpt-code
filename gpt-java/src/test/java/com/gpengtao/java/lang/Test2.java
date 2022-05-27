package com.gpengtao.java.lang;

/**
 * @author pengtao.geng on 2021/4/9 6:59 下午
 */
public class Test2 {

	public static void main(String[] args) {
		A a = new B();

		print(a);
	}

	private static void print(A a) {
		B b = (B) a;
		System.out.println(b);
	}

	public static class A {
		int a;
	}

	public static class B extends A {
		int b;
	}
}





