package com.gpengtao.java.exception;


/**
 * @author pengtao.geng on 2020/4/16 4:56 下午
 */
public class TestThrowable {

	public static void main(String[] args) {
		try {
			fun();
		} catch (Throwable e) {
			sneakyThrow(e);
		}
	}

	private static void fun() throws Exception {
		throw new Exception("gpt test");
	}

	private static RuntimeException sneakyThrow(Throwable t) {
		mySneakyThrow0(t);
		return null;
	}

	@SuppressWarnings("unchecked")
	private static <T extends Throwable> void mySneakyThrow0(Throwable t) throws T {
		throw (T) t;
	}

}
