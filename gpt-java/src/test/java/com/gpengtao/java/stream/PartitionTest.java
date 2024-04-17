package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2024/4/17 16:18.
 */
public class PartitionTest {

	@Test
	public void test_partition_empty_list() {
		// 空list
		List<Integer> list = Lists.newArrayList();

		// 分组
		Map<Boolean, List<Integer>> partition = list.stream().collect(Collectors.partitioningBy(item -> item > 100));

		// 分组后两个组都是空list，不是null
		System.out.println(partition.get(true));
		System.out.println(partition.get(false));
	}
}
