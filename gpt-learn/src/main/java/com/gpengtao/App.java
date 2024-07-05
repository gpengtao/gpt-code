package com.gpengtao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		for (BigDecimal speed = new BigDecimal(8); speed.compareTo(new BigDecimal(11)) <= 0; speed = speed.add(new BigDecimal("0.1"))) {
			BigDecimal time = new BigDecimal(60).divide(speed, 6, RoundingMode.HALF_UP);
			System.out.println(speed + "  " + time.intValue() + "'" + time.subtract(new BigDecimal(time.intValue())).multiply(new BigDecimal(60)).setScale(0, RoundingMode.HALF_DOWN));
		}
	}
}
