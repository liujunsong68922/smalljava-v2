package com.smalljava.l6_vm.memory.impl;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.smalljava.l6_vm.memory.IMemory;

/**
 * MEMO:使用HashMap来模拟内存访问
 * @author liujunsong
 *
 */
public class DefaultHashMapMemoryImpl implements IMemory {
	private static HashMap<String,String> localmap = new HashMap<String,String>();
	@Override
	public void set(String key, String value) {
		localmap.put(key, value);
		
	}

	@Override
	public String get(String key) {
		return localmap.get(key);
	}

	@Override
	public void show() {
		String s1 = JSON.toJSONString(localmap);
		System.out.println(s1);
		
	}

}
