package com.smalljava.l6_vm.eval.plugin;

import java.util.HashMap;

import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;

/**
 * MEMO:����һ��������
 * @author liujunsong
 *
 */
public class IEvalPluginManager {
	private static Logger logger = LoggerFactory.getLogger(IEvalPluginManager.class);
	/**
	 * MEMO: �������
	 */
	private static HashMap<String,IEval> pluginMap = new HashMap<String,IEval>();
	/**
	 * MEMO�����ݲ���������ȡ��Ӧ�Ĳ������
	 * @param opercode
	 * @return
	 */
	public static IEval getIEval(String opercode) {
		initMap();
		if(opercode == null) {
			logger.error("opercode is null");
			return null;
		}else {
			/**
			 * MEMO:����opercode�������Ҵ�����
			 */
			return pluginMap.get(opercode);
		}
	}
	
	private static void initMap() {
		if(pluginMap.size()==0) {
			pluginMap.put("NEW", new NewAddrOperEval());
			pluginMap.put("GET", new GetOperEval());
			pluginMap.put("SET", new SetOperEval());
		}
	}
}
