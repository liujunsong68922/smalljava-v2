package com.smalljava.l6_vm.memory.impl;

import com.smalljava.l6_vm.memory.IMemory;

public class IMemoryFactory {
	public static IMemory getIMemory(String name) {
		if(name == null) {
			return null;
		}
		//暂时只提供一种实现，使用HashMap来实现
		//未来迁移到没有这个数据结构的平台时，
		//需要提供其他的实现方式
		return new DefaultHashMapMemoryImpl();
	}
}
