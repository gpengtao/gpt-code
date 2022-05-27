package com.gpengtao.java.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by pengtao.geng on 2021/9/9 4:15 下午.
 */
public class MapTest {

	@Test
	public void test_map_compute() {
		Map<String, List<Integer>> map = Maps.newHashMap();

		for (int i = 0; i < 10; i++) {
			map.compute("a", (key, value) -> {
				if (value == null) {
					value = Lists.newArrayList(100);
				} else {
					value.add(100);
				}
				return value;
			});

			System.out.println(map);
		}
	}

	@Test
	public void test_map_compute_2() {
		Map<String, List<String>> map1 = Maps.newHashMap();
		map1.put("1", Lists.newArrayList("a"));
		map1.put("2", Lists.newArrayList("b"));

		Map<String, List<String>> map2 = Maps.newHashMap();
		map2.put("1", Lists.newArrayList("a"));
		map2.put("2", Lists.newArrayList("b"));
		map2.put("3", Lists.newArrayList("c"));

		map2.forEach((addKey, addValue) -> {
			map1.compute(addKey, (k, v) -> {
				if (v == null) {
					return addValue;
				} else {
					v.addAll(addValue);
					return v;
				}
			});
		});

		System.out.println(map1);
		System.out.println(map2);
	}
}
