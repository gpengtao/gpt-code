package com.gpengtao.java.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengtao.geng on 2021/5/10 3:50 下午
 */
public class MapReduceTest {

    @Test
    public void test_map_reduce() {
        List<Integer> list = Lists.newArrayList(1, 1, 2, 2, 3);

        Map<String, Integer> result = list.stream()
                .map(index -> {
                    HashMap<String, Integer> map = Maps.newHashMap();
                    map.put(index + "aaa", index);
                    map.put(index + "bbb", index);
                    return map;
                })
                .reduce(Maps.newHashMap(), (map1, map2) -> {
                    map2.forEach((key, value) -> map1.merge(key, value, Integer::sum));
                    return map1;
                });

        System.out.println(result);
    }
}
