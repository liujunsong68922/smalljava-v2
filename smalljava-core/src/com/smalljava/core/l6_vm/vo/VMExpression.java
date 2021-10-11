package com.smalljava.core.l6_vm.vo;

/**
 * VM的表达式定义
 * @author liujunsong
 *
 */
public class VMExpression {
	/**
	 * MEMO:操作码
	 */
	private String code;
	/**
	 * MEMO：地址1
	 */
	private String addr1;
	/**
	 * MEMO：地址2
	 */
	private String addr2;
	/**
	 * MEMO：地址3
	 */
	private String addr3;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	
	
}
