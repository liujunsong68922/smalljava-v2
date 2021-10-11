package com.smalljava.core.l6_vm.eval.plugin;

import com.smalljava.core.l6_vm.memory.IMemory;
import com.smalljava.core.l6_vm.memory.impl.IMemoryFactory;
import com.smalljava.core.l6_vm.vo.VMExpression;

public class GetOperEval implements IEval {

	@Override
	public String eval(VMExpression vo) {
		//¶ÁÈ¡ÄÚ´æ
		IMemory m1 = IMemoryFactory.getIMemory("default");
		return m1.get(vo.getAddr1());
	}
	
}
