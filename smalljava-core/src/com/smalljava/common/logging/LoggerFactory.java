package com.smalljava.common.logging;

public class LoggerFactory {
	public static Logger getLogger(Class _class) {
		return new Logger(_class);
	}
}
