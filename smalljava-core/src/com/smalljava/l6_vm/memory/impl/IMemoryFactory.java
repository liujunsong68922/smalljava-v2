package com.smalljava.l6_vm.memory.impl;

import com.smalljava.l6_vm.memory.IMemory;

public class IMemoryFactory {
	public static IMemory getIMemory(String name) {
		if(name == null) {
			return null;
		}
		//��ʱֻ�ṩһ��ʵ�֣�ʹ��HashMap��ʵ��
		//δ��Ǩ�Ƶ�û��������ݽṹ��ƽ̨ʱ��
		//��Ҫ�ṩ������ʵ�ַ�ʽ
		return new DefaultHashMapMemoryImpl();
	}
}
