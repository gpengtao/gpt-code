package com.gpengtao.test.other;

import com.google.common.io.Files;
import com.gpengtao.utils.JsonUtil;
import lombok.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 *
 */
public class RunChinaTest {

	@SneakyThrows
	@Test
	public void test_run_china() {

//		Param param = Param.builder()
//				.pageNo(1)
//				.pageSize(30)
//				.build();
//		Content content = Request.Post("https://api-changzheng.chinaath.com/changzheng-content-center-api/api/homePage/official/searchCompetitionMls")
//				.connectTimeout(10000)
//				.socketTimeout(10000)
//				.bodyString(JsonUtil.toJson(param), ContentType.APPLICATION_JSON)
//				.execute()
//				.returnContent();
//		String result = new String(content.asBytes());

		String json = FileUtils.readFileToString(new File(this.getClass().getResource("/json/run_china.json").getFile()), Charset.defaultCharset());
		Ret ret = JsonUtil.of(json, Ret.class);
		ret.getData().getResults();
		System.out.println(ret);
		FileUtils.writeStringToFile(new File("out/run_china.txt"), JsonUtil.toJson(ret));
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

	/*
	{
  "success": true,
  "code": 0,
  "msg": "SUCCESS",
  "data": {
    "results": [
      {
        "raceId": 1000193005,
        "raceName": "迎新仙岛岱山半程马拉松赛",
        "raceGrade": "C（属地办赛）",
        "raceTime": "2024-12-21",
        "raceAddress": "浙江省/舟山市/岱山县",
        "raceItem": "[\"半程\"]",
        "raceScale": "200人"
      }
    ],
    "pageNo": 1,
    "pageSize": 30,
    "pageCount": 63,
    "totalCount": 1874,
    "searchAfterFlag": false,
    "lastSortMap": null
  }
}

	* */

}
