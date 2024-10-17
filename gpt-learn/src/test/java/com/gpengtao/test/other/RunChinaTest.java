package com.gpengtao.test.other;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.gpengtao.utils.JsonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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

/**
 *
 */
@Slf4j
public class RunChinaTest {

	@SneakyThrows
	@Test
	public void write_race_json_file() {
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
	public void write_race_list_md() {
		// 读取文件中的json
		String json = FileUtils.readFileToString(new File("out/run_china.json"), Charset.defaultCharset());
		List<Race> allRaces = JsonUtil.ofList(json, Race.class);
		
		write(allRaces);
	}

	public static String toRaceTableRow(Race race) {
		// |开赛时间|比赛名称|赛事等级|比赛地点|比赛项目|赛事规模|
		String raceName = String.format("[%s](https://www.runchina.org.cn/#/race/v/detail/%s \"%s\")", race.getRaceName(), race.getRaceId(), race.getRaceName());
		List<String> item = StringUtils.isEmpty(race.getRaceItem()) ? Lists.newArrayList() : JsonUtil.ofList(race.getRaceItem(), String.class);
		String content = String.join("|",
				Lists.newArrayList(
						race.getRaceTime(),
						raceName,
						race.getRaceAddress(),
						race.getRaceGrade(),
						String.join("、", Objects.requireNonNull(item)),
						Strings.nullToEmpty(race.getRaceScale()),
						race.getRaceId()
				));
		return "|" + content + "|";
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Param {
		private int pageNo;
		private int pageSize;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Ret {
		private boolean success;
		private RetData data;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class RetData {
		private List<Race> results;
		private int pageNo;
		private int pageSize;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Race {
		private String raceId;
		private String raceName;
		private String raceGrade;
		private String raceTime;
		private String raceAddress;
		private String raceItem;
		private String raceScale;
	}

	@SneakyThrows
	private static void write(List<Race> races) {
		// 创建一个配置实例
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File("/Users/pengtao.geng/gpt_work/gpt-code/gpt-learn/src/test/resources/templates"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);

		// 创建数据模型
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("races", races);

		try {
			// 加载模板
			Template template = cfg.getTemplate("tableTemplate.ftl");

			// 创建一个 Writer 对象来写入文件
			Writer out = new FileWriter("/Users/pengtao.geng/gpt_work/gpt-code/gpt-learn/out/output.html");

			// 合并模板和数据模型，写入文件
			template.process(dataModel, out);

			// 关闭 Writer
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
