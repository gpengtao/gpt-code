package com.gpengtao.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * @author pengtao.geng on 2024/10/17 23:06.
 */
public class Safes {

	public static <T> List<T> of(List<T> source) {
		return Optional.ofNullable(source).orElse(Lists.newArrayListWithCapacity(0));
	}
}
