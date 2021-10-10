package com.tools;

import java.util.Arrays;
import java.util.List;

public class TestLambda {
	public static void main(String args[]) {
		String[] str = new String[] { "a", "b", "c" };
		List<String> players = Arrays.asList(str);
		players.forEach((s1) -> {
			System.out.println(getValue() + s1);
		});
	}

	private static String getValue() {
		return "value:";
	}
}
