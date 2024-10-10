package com.gpengtao.test.other;

import com.gpengtao.utils.JsonUtil;
import lombok.*;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@SneakyThrows
	@Test
	public void test() {
		Param param = Param.builder()
				.pageNo(1)
				.pageSize(30)
				.build();
		Content content = Request.Post("https://api-changzheng.chinaath.com/changzheng-content-center-api/api/homePage/official/searchCompetitionMls")
				.connectTimeout(10000)
				.socketTimeout(10000)
				.bodyString(JsonUtil.toJson(param), ContentType.APPLICATION_JSON)
				.execute()
				.returnContent();
		String result = new String(content.asBytes());
		System.out.println(result);
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Param {
		private int pageNo;
		private int pageSize;
	}

	public static void main(String[] args) {
		IntStream.of(-2, -3).forEach(System.out::println);
	}
}
