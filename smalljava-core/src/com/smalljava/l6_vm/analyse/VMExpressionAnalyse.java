package com.smalljava.l6_vm.analyse;

import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;
import com.smalljava.l6_vm.vo.VMExpression;

public class VMExpressionAnalyse {
	/**
	 * MEMO：日志对象
	 */
	private Logger logger = LoggerFactory.getLogger(VMExpressionAnalyse.class);

	/**
	 * MEMO：将给定的str1用逗号进行分解，获取到VMExpression对象
	 * 
	 * @param str1
	 * @return
	 */
	public VMExpression analyse(String str1) {
		if (str1 == null) {
			logger.error("传入字符串为null.");
			return null;
		}
		// TODO:此处先采用简易的实现，下一步再进行细化
		// 直接转换有一个潜在风险，原始输出传入时带逗号，可能造成错误。

		String sdata[] = str1.split(",");
		if (sdata.length < 2) {
			logger.error("数组长度至少为2");
			return null;
		}
		VMExpression vo = new VMExpression();
		//操作符
		vo.setCode(sdata[0]);
		//第一个操作数
		vo.setAddr1(sdata[1]);
		if (sdata.length > 2) {
			//第二个操作数
			vo.setAddr2(sdata[2]);
		}
		if (sdata.length > 3) {
			//第三个操作数
			vo.setAddr3(sdata[3]);
		}
		return vo;
	}
}
