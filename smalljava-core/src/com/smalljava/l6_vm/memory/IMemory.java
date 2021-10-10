package com.smalljava.l6_vm.memory;

public interface IMemory {
	/**
	 * MEMO：写入内存中
	 * @param key	内存地址
	 * @param value	写入值
	 */
	public void set(String key,String value);
	
	/**
	 * MEMO:读取内存
	 * @param key 内存地址
	 * @return
	 */
	public String get(String key);
	/**
	 * MEMO:内存显示
	 */
	public void show();
}
