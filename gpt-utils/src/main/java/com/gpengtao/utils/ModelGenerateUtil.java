package com.gpengtao.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Created by pengtao.geng on 2015/10/27.
 */
public class ModelGenerateUtil {

	/**
	 * 生成class对应的对象
	 *
	 * @param clazz          主类型
	 * @param typeParameters 关联的泛型类型，比如List、Map对应的泛型类型
	 * @param <T>            泛型
	 * @return model
	 */
	public static <T> T generateModel(Class<T> clazz, Type... typeParameters) {
		return doGenerate(clazz, null, typeParameters);
	}

	/**
	 * 生成对象，递归调用
	 *
	 * @param <T>            泛型
	 * @param clazz          主类型
	 * @param valuePatten    string类型时，字符串前缀
	 * @param typeParameters 这个类相关的泛型类型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	private static <T> T doGenerate(Class<?> clazz, String valuePatten, Type... typeParameters) {
		// String
		if (clazz.isAssignableFrom(String.class)) {
			if (maybeDateString(valuePatten)) {
				return (T) randomDateString();
			} else {
				return (T) randomString(valuePatten);
			}
		}

		// int
		if (clazz.isAssignableFrom(int.class) || clazz.isAssignableFrom(Integer.class)) {
			return (T) randomInt();
		}

		// long
		if (clazz.isAssignableFrom(long.class) || clazz.isAssignableFrom(Long.class)) {
			return (T) randomLong();
		}

		// double
		if (clazz.isAssignableFrom(double.class) || clazz.isAssignableFrom(Double.class)) {
			return (T) randomDouble();
		}

		// boolean
		if (clazz.isAssignableFrom(boolean.class) || clazz.isAssignableFrom(Boolean.class)) {
			return (T) randomBoolean();
		}

		// BigDecimal
		if (clazz.isAssignableFrom(BigDecimal.class)) {
			return (T) randomBigDecimal();
		}

		// Date
		if (clazz.isAssignableFrom(Date.class)) {
			return (T) randomDate();
		}

		// 枚举类型
		if (clazz.isEnum()) {
			int a = 0;
			Object[] enumConstants = clazz.getEnumConstants();
			if (enumConstants.length > 0) {
				return (T) enumConstants[0];
			} else {
				return null;
			}
		}

		// List
		if (clazz.isAssignableFrom(List.class) || clazz.isAssignableFrom(ArrayList.class)) {
			Object[] twoObj = generateTwoObjForType(typeParameters[0]);

			return (T) Lists.newArrayList(twoObj[0], twoObj[1]);
		}

		// Map
		if (clazz.isAssignableFrom(Map.class) || clazz.isAssignableFrom(HashMap.class)) {
			Object[] twoKey = generateTwoObjForType(typeParameters[0]);
			Object[] twoValue = generateTwoObjForType(typeParameters[1]);

			Map<Object, Object> map = Maps.newHashMap();
			map.put(twoKey[0], twoValue[0]);
			map.put(twoKey[1], twoValue[1]);
			return (T) map;
		}

		// 自定义类
		return (T) doGenerateForUserClazz(clazz);
	}

	private static Object[] generateTwoObjForType(Type actualType) {
		Object[] objects = new Object[2];

		if (actualType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) actualType;
			objects[0] = doGenerate((Class<?>) parameterizedType.getRawType(), null, parameterizedType.getActualTypeArguments());
			objects[1] = doGenerate((Class<?>) parameterizedType.getRawType(), null, parameterizedType.getActualTypeArguments());
		} else {
			objects[0] = doGenerate((Class<?>) actualType, null, (Type) null);
			objects[1] = doGenerate((Class<?>) actualType, null, (Type) null);
		}

		return objects;
	}

	public static <T> T doGenerateForUserClazz(Class<T> clazz) {
		try {
			// 默认构造方法构建对象
			T model = clazz.newInstance();

			// 获得属性，包括父类的
			List<Field> declaredFields = getInheritedFields(clazz);

			// 获得方法，包括父类的
			List<Method> declaredMethods = getInheritedMethods(clazz);

			// 遍历声明的属性，匹配set方法，设置属性的值
			declaredFields.stream()
					.filter(field -> !Modifier.isStatic(field.getModifiers()))
					.filter(field -> !"serialVersionUID".equals(field.getName()))
					.forEach(field -> {
						Method setterMethod = findMatchedMethod(declaredMethods, field);
						if (setterMethod == null) {
							return;
						}

						fillValue(model, field, setterMethod);
					});

			return model;
		} catch (Exception e) {
			throw new RuntimeException("异常", e);
		}
	}

	private static List<Field> getInheritedFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	private static List<Method> getInheritedMethods(Class<?> type) {
		List<Method> fields = new ArrayList<>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredMethods()));
		}
		return fields;
	}

	private static Method findMatchedMethod(List<Method> declaredMethods, Field field) {
		String setterMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

		return declaredMethods.stream()
				// 名字匹配
				.filter(method -> Objects.equals(method.getName(), setterMethodName))
				// 参数匹配，举例：field abc 的类型是int，但是setAbc(long x)参数可能是long类型
				.filter(method -> {
					Class<?>[] parameterTypes = method.getParameterTypes();
					return parameterTypes.length == 1 && parameterTypes[0] == field.getType();
				})
				.findFirst()
				.orElse(null);
	}

	private static <T> void fillValue(T model, Field field, Method method) {
		try {
			// 构造一个field所属类型的值
			Object value = doGenerateForField(field);

			// invoke
			method.invoke(model, value);
		} catch (Exception e) {
			throw new RuntimeException("fillValue异常", e);
		}
	}

	private static <T> T doGenerateForField(Field field) {
		// field上的泛化类型
		Type genericType = field.getGenericType();

		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			return doGenerate(field.getType(), field.getName(), parameterizedType.getActualTypeArguments());
		} else {
			return doGenerate(field.getType(), field.getName(), (Type) null);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static Object randomString(String patten) {
		if (patten != null) {
			return patten + randomInt();
		} else {
			return "string" + randomInt();
		}
	}

	private static Object randomInt() {
		return new Random().nextInt(20);
	}

	private static Object randomLong() {
		return (long) new Random().nextInt(20);
	}

	private static Object randomDouble() {
		return (int) randomInt() + 0.5;
	}

	private static Object randomBoolean() {
		return new Random().nextBoolean();
	}

	private static Object randomBigDecimal() {
		return new BigDecimal((int) randomInt());
	}

	private static Object randomDate() {
		return DateUtils.addDays(new Date(), (int) randomInt());
	}

	private static String randomDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format((Date) randomDate());
	}

	private static boolean maybeDateString(String setter) {
		return StringUtils.endsWith(setter, "date");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
