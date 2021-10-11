package com.smalljava.core.l6_vm;

import com.smalljava.core.l6_vm.analyse.VMExpressionAnalyse;
import com.smalljava.core.l6_vm.eval.VMExpressionEval;
import com.smalljava.core.l6_vm.memory.IMemory;
import com.smalljava.core.l6_vm.memory.impl.IMemoryFactory;
import com.smalljava.core.l6_vm.vo.VMExpression;

public class TestVMEval {
	public static void main(String args[]) {
		VMExpressionAnalyse analyse = new VMExpressionAnalyse();
		VMExpressionEval eval = new VMExpressionEval();
		IMemory m1 = IMemoryFactory.getIMemory("default");

		for (int i = 0; i < 100; i++) {
			String s1 = "NEW,0,0,0";
			String sret = eval.eval(analyse.analyse(s1));
			System.out.println(sret);

			m1.show();

			s1 = "SET," + sret + ",100";
			sret = eval.eval(analyse.analyse(s1));
			System.out.println(sret);
			m1.show();
		}

	}
}
