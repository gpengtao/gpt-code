package com.gpengtao.utils;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pengtao.geng on 2022/6/23 19:37.
 */
public class ModeGenerateTest {

	@Test
	public void test_generate_string() {
		String str = ModelGenerateUtil.generateModel(String.class);
		System.out.println(str);
	}

	@Test
	public void test_generate_double() {
		System.out.println(ModelGenerateUtil.generateModel(Double.class));
	}

	@Test
	public void test_generate_model() {
		Model model = ModelGenerateUtil.generateModel(Model.class);
		System.out.println(model);
	}

	@Test
	public void test_generate_list() {
		List<Model> aos = Lists.newArrayList();
		List list = ModelGenerateUtil.generateModel(aos.getClass(), Model.class);
		System.out.println(list);
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Model {
		private Map<String, Double> skuPriceMap;

		private List<Long> orderIds;

		private String targetDate;

		private boolean onSale;

		private Integer age;

		private Long length;

		private BigDecimal price;

		private Date createTime;

		private List<ModelSub> subList;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ModelSub {

		private String subName;

		private List<List<List<List<ModelSubItem>>>> items;

		private Map<List<Integer>, Map<ModelSubItem, Map<String, Double>>> map;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ModelSubItem {
		private String itemName;
	}
}
