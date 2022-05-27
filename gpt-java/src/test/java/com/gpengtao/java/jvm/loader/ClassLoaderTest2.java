package com.gpengtao.java.jvm.loader;

import org.junit.Test;

import java.net.URL;

/**
 * @author pengtao.geng on 2018/8/16 下午9:05.
 */
public class ClassLoaderTest2 {

	/**
	 * classloader Bootstrap加载哪些类
	 */
	@Test
	public void test_class_loader_bootstrp_load_which_class() {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}

		System.out.println(System.getProperty("sun.boot.class.path"));
	}

	@Test
	public void test(){
		System.out.println(ClassLoader.getSystemClassLoader());
	}
}
