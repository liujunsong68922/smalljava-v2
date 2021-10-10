package com.smalljava.l6_vm.analyse;

import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;
import com.smalljava.l6_vm.vo.VMExpression;

public class VMExpressionAnalyse {
	/**
	 * MEMO����־����
	 */
	private Logger logger = LoggerFactory.getLogger(VMExpressionAnalyse.class);

	/**
	 * MEMO����������str1�ö��Ž��зֽ⣬��ȡ��VMExpression����
	 * 
	 * @param str1
	 * @return
	 */
	public VMExpression analyse(String str1) {
		if (str1 == null) {
			logger.error("�����ַ���Ϊnull.");
			return null;
		}
		// TODO:�˴��Ȳ��ü��׵�ʵ�֣���һ���ٽ���ϸ��
		// ֱ��ת����һ��Ǳ�ڷ��գ�ԭʼ�������ʱ�����ţ�������ɴ���

		String sdata[] = str1.split(",");
		if (sdata.length < 2) {
			logger.error("���鳤������Ϊ2");
			return null;
		}
		VMExpression vo = new VMExpression();
		//������
		vo.setCode(sdata[0]);
		//��һ��������
		vo.setAddr1(sdata[1]);
		if (sdata.length > 2) {
			//�ڶ���������
			vo.setAddr2(sdata[2]);
		}
		if (sdata.length > 3) {
			//������������
			vo.setAddr3(sdata[3]);
		}
		return vo;
	}
}
