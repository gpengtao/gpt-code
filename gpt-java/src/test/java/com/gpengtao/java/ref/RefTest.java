package com.gpengtao.java.ref;

import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengtao.geng on 2019-05-31 22:55
 */
public class RefTest {

	@Test
	public void test() {
		// 软引用
		SoftReference<String> sr = new SoftReference<>("soft" + Integer.valueOf("1"));
		System.out.println(sr.get());
		System.gc();
		System.out.println(sr.get());

		System.out.println("================================");

		// 弱引用
		WeakReference<String> sr2 = new WeakReference<>("weak" + Integer.valueOf("2"));
		System.out.println(sr2.get());
		System.gc();
		System.out.println(sr2.get());

		System.out.println("================================");

		ReferenceQueue<String> queue = new ReferenceQueue<>();
		PhantomReference<String> pr = new PhantomReference<>("hello" + Integer.valueOf("3"), queue);
		System.out.println(pr.get());
	}

	public static void main(String[] args) {
		// 软引用
		SoftReference<List<long[]>> softReference = new SoftReference<>(allocateBigArray());
		System.out.println("软引用对象：" + softReference.get());

		printGcInfo();

		System.out.println("================再次分配大对象================");

		List<long[]> list = allocateBigArray();
		System.out.println("分配大对象：" + list);
		System.out.println("软引用对象：" + softReference.get());

		printGcInfo();
	}

	private static void printGcInfo() {
		List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
		beans.forEach(bean -> System.out.println(bean.getName() + " gc次数是：" + bean.getCollectionCount()));
	}

	private static List<long[]> allocateBigArray() {
		List<long[]> list = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			list.add(new long[100000000]);
		}
		return list;
	}
}
