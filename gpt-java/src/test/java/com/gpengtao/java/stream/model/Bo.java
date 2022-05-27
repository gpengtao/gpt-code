package com.gpengtao.java.stream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengtao.geng on 2019/11/7 6:56 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bo {
	private String name;
	private Score score;
	private Integer age;
}
