package com.gpengtao.java.stream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengtao.geng on 2019/11/7 6:57 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {

	private Integer score;

	public static Score sum(Score a, Score b) {
		a.setScore(a.getScore() + b.getScore());
		return a;
	}

	public static Score init() {
		return new Score(0);
	}
}
