package com.smalljava.l6_vm.memory;

public interface IMemory {
	/**
	 * MEMO��д���ڴ���
	 * @param key	�ڴ��ַ
	 * @param value	д��ֵ
	 */
	public void set(String key,String value);
	
	/**
	 * MEMO:��ȡ�ڴ�
	 * @param key �ڴ��ַ
	 * @return
	 */
	public String get(String key);
	/**
	 * MEMO:�ڴ���ʾ
	 */
	public void show();
}
