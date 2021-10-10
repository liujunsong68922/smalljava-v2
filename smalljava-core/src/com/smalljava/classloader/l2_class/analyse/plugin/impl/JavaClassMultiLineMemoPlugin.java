package com.smalljava.classloader.l2_class.analyse.plugin.impl;

import com.smalljava.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.classloader.l2_class.vo.element.JavaClassMultiLineMemoElement;
import com.smalljava.common.StringFindUtil;
import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;

public class JavaClassMultiLineMemoPlugin implements IJavaClassAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaClassMultiLineMemoPlugin.class);
	
	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (strdata.startsWith("/*")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = util.findfirstStringForBlock(strdata, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				logger.error("��ERROR���Ҳ���*/");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 2);
				logger.debug(strmemo);
				
				String strleftstring;
				if(ilineendpos<strdata.length()-2) {
					strleftstring = strdata.substring(ilineendpos + 2);
				}else {
					strleftstring = "";
				}

				JavaClassMultiLineMemoElement ele = new JavaClassMultiLineMemoElement();
				ele.setStringcontent(strmemo);
				ele.setComputeleftstring(strleftstring);
				return ele;
			}
		} else {
			return null;
		}
	}


}
