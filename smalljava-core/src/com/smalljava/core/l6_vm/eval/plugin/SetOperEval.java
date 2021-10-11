package com.smalljava.core.l6_vm.eval.plugin;

import com.smalljava.core.l6_vm.memory.IMemory;
import com.smalljava.core.l6_vm.memory.impl.IMemoryFactory;
import com.smalljava.core.l6_vm.vo.VMExpression;

/**
 * MEMO: 虚拟机上的SET命令，写入一个数据，参数1代表地址，参数2代表内容
 * @author liujunsong
 *
 */
public class SetOperEval implements IEval {

	@Override
	public String eval(VMExpression vo) {
		//写入内存中
		IMemory m1 = IMemoryFactory.getIMemory("default");
		m1.set(vo.getAddr1(),vo.getAddr2());
		return vo.getAddr2();
	}

}
