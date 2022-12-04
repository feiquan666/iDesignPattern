package cn.javaunion.utils;

public class Assert {

	public static void notNull(Object o, String msg) {
		if (o == null) {
			throw new RuntimeException(msg);
		}
	}

	public static void isTrue(boolean condition, String msg) {
		if (condition) {
			throw new RuntimeException(msg);
		}
	}

}
