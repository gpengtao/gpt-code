package com.gpengtao.java.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author pengtao.geng on 2019/10/23 10:23 下午
 */
public class GptTest {

	@Test
	public void test() {
		Integer xxx = new Integer(10000);

		print(xxx);

		List<Integer> list = Lists.newArrayList(1, 2, 3);
		list.forEach(i -> {
			System.out.println(xxx);
			System.out.println(getClass());
			System.out.println(this);
			System.out.println(i);
		});
	}

	public void print(Integer aaa) {
		System.out.println(aaa);
	}

	/**
	 * 匿名内部类的命名规则 OutClass$n
	 */
	@Test
	public void test_function() {
		Function function1 = new Function() {
			@Override
			public Object apply(Object o) {
				return "hello";
			}
		};

		Function function2 = new Function() {
			@Override
			public Object apply(Object o) {
				return "world";
			}
		};

		Runnable run = new Runnable() {
			@Override
			public void run() {
				System.out.println("run");
			}
		};

		Human human = new Human() {
			@Override
			public void sayHello() {
				System.out.println("hello");
			}
		};

		System.out.println(function1);
		System.out.println(function2);
		System.out.println(run);
		System.out.println(human);
	}

	@Test
	public void test_int_stream(){
		IntStream.rangeClosed(1,10).forEach(x-> System.out.println(x));
	}
}
