package com.smalljava.core.l6_vm.eval.plugin;

import com.smalljava.core.l6_vm.memory.IMemory;
import com.smalljava.core.l6_vm.memory.impl.IMemoryFactory;
import com.smalljava.core.l6_vm.vo.VMExpression;

/**
 * MEMO: ������ϵ�SET���д��һ�����ݣ�����1�����ַ������2��������
 * @author liujunsong
 *
 */
public class SetOperEval implements IEval {

	@Override
	public String eval(VMExpression vo) {
		//д���ڴ���
		IMemory m1 = IMemoryFactory.getIMemory("default");
		m1.set(vo.getAddr1(),vo.getAddr2());
		return vo.getAddr2();
	}

}
