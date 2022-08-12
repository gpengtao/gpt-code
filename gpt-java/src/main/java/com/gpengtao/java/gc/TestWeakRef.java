package com.gpengtao.java.gc;

import java.lang.ref.WeakReference;

/**
 * @author pengtao.geng on 2020/1/8 5:14 下午
 */
public class TestWeakRef {

	private static void invokeGc() {
		Object obj = new Object();
		WeakReference ref = new WeakReference<Object>(obj);
		obj = null;

		while (ref.get() != null) {
			System.out.println("invoke gc");
			System.gc();
		}
	}

	public static void main(String[] args) {
		invokeGc();
	}

}
