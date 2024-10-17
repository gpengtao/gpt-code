package com.gpengtao.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengtao.geng on 2024/7/11 20:16.
 */
public class JsonUtil {

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@SneakyThrows
	public static String toJson(Object obj) {
		if (null == obj) {
			return null;
		}
		return OBJECT_MAPPER.writeValueAsString(obj);
	}

	@SneakyThrows
	public static <T> T of(String json, Class<T> tClass) {
		if (Strings.isNullOrEmpty(json)) {
			return null;
		}
		return OBJECT_MAPPER.readValue(json, tClass);
	}

	@SneakyThrows
	public static <T> List<T> ofList(String json, Class<T> tClass) {
		if (Strings.isNullOrEmpty(json)) {
			return Lists.newArrayList();
		}
		JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, tClass);
		return OBJECT_MAPPER.readValue(json, javaType);
	}
}
