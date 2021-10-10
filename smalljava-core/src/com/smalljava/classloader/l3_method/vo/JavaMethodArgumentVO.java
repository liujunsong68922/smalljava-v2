package com.smalljava.classloader.l3_method.vo;

/**
 * Java Method定义的参数定义
 * @author liujunsong
 *
 */
public class JavaMethodArgumentVO {
	/**
	 * 方法参数类型
	 */
	private String argtype;
	
	/**
	 * 方法参数名
	 */
	private String argname;

	public String getArgname() {
		return argname;
	}
	public void setArgname(String argname) {
		this.argname = argname;
	}
	public String getArgtype() {
		return argtype;
	}
	public void setArgtype(String argtype) {
		this.argtype = argtype;
	}
	
}
