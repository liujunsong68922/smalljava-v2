package com.smalljava.core.l6_vm.eval;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l6_vm.eval.plugin.IEval;
import com.smalljava.core.l6_vm.eval.plugin.IEvalPluginManager;
import com.smalljava.core.l6_vm.vo.VMExpression;

public class VMExpressionEval {
	Logger logger = LoggerFactory.getLogger(VMExpressionEval.class);
	/**
	 * MEMO�����������ִ��ָ�
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
			//���ò��ִ��
			return plugin.eval(vo);
		}
	}
}