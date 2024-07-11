package com.gpengtao.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengtao.geng on 2024/7/11 20:16.
 */
public class JsonUtil {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@SneakyThrows
	public static String toJson(Object obj) {
		if (null == obj) {
			return null;
		}
		return OBJECT_MAPPER.writeValueAsString(obj);
	}

	@SneakyThrows
	public static <T> List<T> ofList(String json, Class<T> tClass) {
		if (Strings.isNullOrEmpty(json)) {
			return null;
		}
		JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, tClass);
		return OBJECT_MAPPER.readValue(json, javaType);
	}
}
