package com.gpengtao.java.badcode;

import org.junit.Test;

import java.util.Random;

/**
 * @author pengtao.geng on 2019-03-29 12:47
 */
public class BadCode {

	@Test
	public void show_bad_code() {
		if (isOk()) {
			if (isOk()) {
				if (isOk()) {
					if (isOk()) {
						if (isOk()) {

						}
					}
				}
			}
		}
	}

	private boolean isOk() {
		return new Random().nextBoolean();
	}
}
