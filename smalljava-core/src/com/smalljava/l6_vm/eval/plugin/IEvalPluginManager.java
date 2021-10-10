package com.smalljava.l6_vm.eval.plugin;

import java.util.HashMap;

import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;

/**
 * MEMO:这是一个工厂类
 * @author liujunsong
 *
 */
public class IEvalPluginManager {
	private static Logger logger = LoggerFactory.getLogger(IEvalPluginManager.class);
	/**
	 * MEMO: 插件集合
	 */
	private static HashMap<String,IEval> pluginMap = new HashMap<String,IEval>();
	/**
	 * MEMO：根据操作码来获取对应的操作插件
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
			 * MEMO:按照opercode来查表查找处理插件
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
