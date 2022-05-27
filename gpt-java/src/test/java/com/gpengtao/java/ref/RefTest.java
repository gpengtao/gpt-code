package com.gpengtao.java.ref;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author pengtao.geng on 2019-05-31 22:55
 */
public class RefTest {

	@Test
	public void test() {
		// 软引用
		SoftReference<String> sr = new SoftReference<>("hello" + Integer.valueOf("1"));
		System.out.println(sr.get());
		System.gc();
		System.out.println(sr.get());

		// 弱引用
		WeakReference<String> sr2 = new WeakReference<>("hello" + Integer.valueOf("2"));
		System.out.println(sr2.get());
		System.gc();
		System.out.println(sr2.get());

		ReferenceQueue<String> queue = new ReferenceQueue<>();
		PhantomReference<String> pr = new PhantomReference<>("hello" + Integer.valueOf("3"), queue);
		System.out.println(pr.get());

	}
}
