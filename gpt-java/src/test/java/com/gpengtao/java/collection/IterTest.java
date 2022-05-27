package com.gpengtao.java.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器删除元素测试
 *
 * @author pengtao.geng on 2019/11/12 6:45 下午
 */
public class IterTest {

	@Test
	public void test_remove_list_by_fori() {
		List<String> list = new ArrayList<>();
		list.add("del1");
		list.add("del2");
		list.add("del3");
		list.add("del4");
		list.add("del5");
		list.add("del6");

		System.out.println("get list: \n" + list);

		// remove之后list的size变少了，for循环类似于跳步了
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).startsWith("del")) {
				list.remove(i);
			}
		}

		System.out.println(list);
	}

	@Test
	public void test_remove_list_by_fori_backward() {
		List<String> list = new ArrayList<>();
		list.add("del1");
		list.add("del2");
		list.add("del3");

		System.out.println("get list: " + list);

		// fori倒着删除，没问题
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).startsWith("del")) {
				list.remove(i);
			}
		}
		System.out.println(list);
	}

	@Test
	public void test_list_3() {
		List<String> list = new ArrayList<>();
		list.add("del1");
		list.add("del2");
		list.add("del3");

		System.out.println("get list: \n" + list);

		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String value = iter.next();
			if (value.startsWith("del")) {
				// 使用list删除了value，再遍历的时候会使迭代器失败
				list.remove(value);
				System.out.println("list删除之后:" + list);
			}
		}
		System.out.println(list);
	}

	@Test
	public void test_list_4() {
		List<String> list = new ArrayList<>();
		list.add("del1");
		list.add("del2");
		list.add("del3");

		System.out.println("get list: \n" + list);

		// 正确的姿势
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String value = iter.next();
			if (value.startsWith("del")) {
				iter.remove();
			}
		}
		System.out.println(list);
	}

	@Test
	public void test_list_5() {
		List<String> list = new ArrayList<>();
		list.add("del");
		list.add("del");
		list.add("del");

		System.out.println("get list: \n" + list);

		for (String value : list) {
			if (value.equals("del")) {
				list.remove(value);
				// 一次就退出，break是可以的
				// break;
			}
		}

		System.out.println(list);
	}
}
