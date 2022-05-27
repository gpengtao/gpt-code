package com.gpengtao.java.jvm.loader;

/**
 * @author pengtao.geng on 2018/9/13 下午9:50.
 */
public class Demo {

	static {
		System.out.println("static code");
	}

	public static Demo DEMO = new Demo("aaa");

	public Demo(String s) {
		System.out.println(s);
	}

	{
		System.out.println("init");
	}

	public static void main(String[] args) {
		new Demo("xxx");

		new Demo("yyy");
	}
}
