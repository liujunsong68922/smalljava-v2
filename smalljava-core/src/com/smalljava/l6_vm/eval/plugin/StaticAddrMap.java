package com.smalljava.l6_vm.eval.plugin;

import java.util.HashMap;

public class StaticAddrMap {
	/**
	 * MEMO：所有的地址表
	 */
	private static HashMap<String,String> addrmap = new HashMap<String,String>();
	
	public static void addAddr(String addr) {
		addrmap.put(addr, "");
	}
}
