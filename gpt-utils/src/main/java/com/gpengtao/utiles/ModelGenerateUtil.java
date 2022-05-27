package com.gpengtao.utiles;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by pengtao.geng on 2015/10/27.
 */
public class ModelGenerateUtil {

	private static final String SERIAL_VERSION_UID = "serialVersionUID";

	public static <T> T generateModel(Class<T> clazz) {
		try {
			T model = clazz.newInstance();

			// 获得属性，包括父类的
			List<Field> declaredFields = getInheritedFields(clazz);
			// 获得方法，包括父类的
			List<Method> declaredMethods = getInheritedMethods(clazz);
			Set<String> declaredMethodNames = declaredMethods.stream().map(Method::getName).collect(Collectors.toSet());

			// 遍历声明的属性，匹配set方法，设置属性的值
			for (Field field : declaredFields) {
				// 跳过，静态属性
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				// 跳过，序列化属性
				if (SERIAL_VERSION_UID.equals(field.getName())) {
					continue;
				}

				// 得到set方法
				String setter = calcSetMethodName(field.getName());

				// 属性没有暴露set方法，则忽略这个属性
				if (!declaredMethodNames.contains(setter)) {
					continue;
				}

				// 匹配名字和参数都相符的set方法，举例：方法setXXX(int xx)，field的类型不一定是int
				Optional<Method> matchMethod = declaredMethods.stream()
						.filter(method -> method.getName().equals(setter))
						.filter(method -> {
							Class<?>[] parameterTypes = method.getParameterTypes();
							return parameterTypes.length == 1 && parameterTypes[0] == field.getType();
						})
						.findFirst();

				// invoke
				if (matchMethod.isPresent()) {
					// 构造一个field所属类型的值
					Object value = randomMakeValue(field);

					// invoke
					matchMethod.get().invoke(model, value);
				}
			}

			return model;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Object randomMakeValue(Field field) throws ClassNotFoundException {
		String fieldName = field.getName();
		Class<?> fieldType = field.getType();

		// String
		if (fieldType.isAssignableFrom(String.class)) {
			if (isStartOrEndDate(fieldName)) {
				return getDateString();
			} else {
				return fieldName + randomInt();
			}
		}

		// int
		if (fieldType.isAssignableFrom(int.class) || fieldType.isAssignableFrom(Integer.class)) {
			return randomInt();
		}

		// long
		if (fieldType.isAssignableFrom(long.class) || fieldType.isAssignableFrom(Long.class)) {
			return (long) randomInt();
		}

		// double
		if (fieldType.isAssignableFrom(double.class) || fieldType.isAssignableFrom(Double.class)) {
			return new Random().nextDouble();
		}

		// boolean
		if (fieldType.isAssignableFrom(boolean.class) || fieldType.isAssignableFrom(Boolean.class)) {
			return randomBoolean();
		}

		// BigDecimal
		if (fieldType.isAssignableFrom(BigDecimal.class)) {
			return new BigDecimal(randomInt());
		}

		// Date
		if (fieldType.isAssignableFrom(Date.class)) {
			return new Date();
		}

		// List<>
		if (fieldType.isAssignableFrom(List.class)) {
			String fullTypeName = field.getGenericType().getTypeName();
			String listType = fullTypeName.substring(fullTypeName.indexOf("<") + 1, fullTypeName.lastIndexOf(">"));
			Class<?> listClass = Class.forName(listType);
			Object o1 = generateModel(listClass);
			Object o2 = generateModel(listClass);
			return Lists.newArrayList(o1, o2);
		}

		return generateModel(fieldType);
	}

	private static String calcSetMethodName(String fieldName) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		return "set" + firstLetter + fieldName.substring(1);
	}

	private static String getDateString() {
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	private static boolean isStartOrEndDate(String setter) {
		return StringUtils.containsIgnoreCase(setter, "StartDate") || StringUtils.containsIgnoreCase(setter, "EndDate");
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

	private static int randomInt() {
		int i = new Random().nextInt(10);
		return i < 0 ? -i : i;
	}

	private static boolean randomBoolean() {
		return new Random().nextBoolean();
	}
}
