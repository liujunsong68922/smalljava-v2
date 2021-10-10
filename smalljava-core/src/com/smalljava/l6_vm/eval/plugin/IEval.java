package com.smalljava.l6_vm.eval.plugin;

import com.smalljava.l6_vm.vo.VMExpression;

/**
 * MEMO：执行器各插件接口定义
 * @author liujunsong
 *
 */
public interface IEval {
	public String eval(VMExpression vo);
}
