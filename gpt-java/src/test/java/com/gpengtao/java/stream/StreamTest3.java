package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2023/2/10 17:57.
 */
public class StreamTest3 {

	@Test
	public void test_flat_map() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

		List<String> ret = list.stream()
				.map(index -> Lists.newArrayList("a" + index, "b" + index, "c" + index))
				.flatMap(Collection::stream)
				.limit(5)
				.collect(Collectors.toList());

		System.out.println(ret);
	}
}
