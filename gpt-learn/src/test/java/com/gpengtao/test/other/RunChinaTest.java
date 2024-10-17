package com.gpengtao.test.other;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gpengtao.utils.JsonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
public class RunChinaTest {

	@SneakyThrows
	@Test
	public void fetch_race_list_to_file() {
		List<Race> allRaces = Lists.newArrayList();
		for (int pageNo = 1; pageNo < 10000; pageNo++) {
			// 查询
			Param param = Param.builder()
					.pageNo(pageNo)
					.pageSize(30)
					.build();
			Content content = Request.Post("https://api-changzheng.chinaath.com/changzheng-content-center-api/api/homePage/official/searchCompetitionMls")
					.connectTimeout(10000)
					.socketTimeout(10000)
					.bodyString(JsonUtil.toJson(param), ContentType.APPLICATION_JSON)
					.execute()
					.returnContent();
			String result = new String(content.asBytes());
			System.out.println("查询马拉松list页 " + pageNo + " 返回数据是:" + result);

			// 加入结果
			Ret ret = JsonUtil.of(result, Ret.class);
			allRaces.addAll(Objects.requireNonNull(ret).getData().getResults());

			// 是否结束
			if (ret.getData().getResults().size() != 30) {
				break;
			}
		}

		// 写入文件，方便本地再次使用
		FileUtils.writeStringToFile(new File("out/run_china.json"), JsonUtil.toJson(allRaces), Charset.defaultCharset());
	}

	@SneakyThrows
	@Test
	public void fetch_race_detail_to_file() {
		// 读取文件中的json
		String json = FileUtils.readFileToString(new File("out/run_china.json"), Charset.defaultCharset());
		List<Race> allRaces = JsonUtil.ofList(json, Race.class);

		allRaces.stream()
				.limit(1)
				.map(race -> {
					try {
						Map<String, String> param = Maps.newHashMap();
						param.put("id", race.getRaceId());
						param.put("type", "SS");
						Content content = Request.Post("https://api-changzheng.chinaath.com/changzheng-content-center-api/api/homePage/official/searchById")
								.connectTimeout(10000)
								.socketTimeout(10000)
								.bodyString(JsonUtil.toJson(param), ContentType.APPLICATION_JSON)
								.execute()
								.returnContent();

						String result = new String(content.asBytes());
						return result;
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				})
				.collect(Collectors.toList());
	}

	@SneakyThrows
	@Test
	public void write_race_to_html() {
		// 读取文件中的json
		String json = FileUtils.readFileToString(new File("out/run_china.json"), Charset.defaultCharset());
		List<Race> allRaces = JsonUtil.ofList(json, Race.class);

		allRaces.forEach(race -> {
			if (StringUtils.isNotEmpty(race.getRaceItem())) {
				List<String> items = JsonUtil.ofList(race.getRaceItem(), String.class);
				if (CollectionUtils.isNotEmpty(items)) {
					race.setRaceItemForShow(String.join("，", items));
				}
			}
		});

		// https://www.runchina.org.cn/#/race/v/detail/%s

		// 创建一个配置实例
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File("/Users/pengtao.geng/gpt_work/gpt-code/gpt-learn/src/test/resources/templates"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);

		// 创建数据模型
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("races", allRaces);

		try {
			// 加载模板
			Template template = cfg.getTemplate("run_china.ftl");

			// 创建一个 Writer 对象来写入文件
			Writer out = new FileWriter("/Users/pengtao.geng/gpt_work/gpt-code/gpt-learn/马拉松赛历.html");

			// 合并模板和数据模型，写入文件
			template.process(dataModel, out);

			// 关闭 Writer
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Param {
		private int pageNo;
		private int pageSize;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Ret {
		private boolean success;
		private RetData data;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RetData {
		private List<Race> results;
		private int pageNo;
		private int pageSize;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Race {
		private String raceId;
		private String raceName;
		private String raceGrade;
		private String raceTime;
		private String raceAddress;
		private String raceItem;
		private String raceScale;

		private String raceItemForShow;
	}
}
