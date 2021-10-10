package com.smalljava.l6_vm.eval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smalljava.l6_vm.eval.plugin.IEval;
import com.smalljava.l6_vm.eval.plugin.IEvalPluginManager;
import com.smalljava.l6_vm.vo.VMExpression;

public class VMExpressionEval {
	Logger logger = LoggerFactory.getLogger(VMExpressionEval.class);
	/**
	 * MEMO：在虚拟机上执行指令集
	 * @param vo
	 * @return
	 */
	public String eval(VMExpression vo) {
		if(vo == null) {
			logger.error("vo is null.");
			return null;
		}
		IEvalPluginManager pm = new IEvalPluginManager();
		IEval plugin = pm.getIEval(vo.getCode());
		if(plugin == null) {
			logger.error("Cannot find plugin.code:"+vo.getCode());
			return null;
		}else {
			//调用插件执行
			return plugin.eval(vo);
		}
	}
}
