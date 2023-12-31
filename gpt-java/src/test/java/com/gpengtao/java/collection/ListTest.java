package com.gpengtao.java.collection;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pengtao.geng on 2016/10/27.
 */
public class ListTest {

	@Test
	public void retain() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(2, 5, 6, 7, 8);

		boolean b = list1.retainAll(list2);
		System.out.println(b);
		System.out.println(list1);
	}

	@Test
	public void sort() {
		List<Integer> list = Lists.newArrayList(8, 4, 1, 6, 2, 4, 5);

		Collections.sort(list);

		System.out.println(list);
	}

	@Test
	public void test_subtract() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(1, 2, 3);

		// list1 - list2，差值，新list，原来的list1/2不会变
		CollectionUtils.subtract(list1, list2);
	}

	@Test
	public void test_collection() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(1, 2, 3);


		System.out.println(CollectionUtils.subtract(list1, list2));

		System.out.println(CollectionUtils.intersection(list1, list2));
	}

	@Test
	public void test_new() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3);
		List<Integer> list2 = new ArrayList<>(list1);// 重新开闭了list

		System.out.println(list1);
		System.out.println(list2);
	}

	@Test
	public void test_as_list() {
		List<String> list = Arrays.asList("hello", "world");
		list.add("aaa");
	}
}