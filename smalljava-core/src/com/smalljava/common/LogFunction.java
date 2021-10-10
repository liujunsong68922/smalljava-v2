package com.smalljava.common;

public class LogFunction {
	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void log2(String prefix,String s) {
		System.out.println("["+prefix+"]: "+s);
	}
}
