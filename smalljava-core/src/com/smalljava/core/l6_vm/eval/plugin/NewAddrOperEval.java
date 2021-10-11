package com.smalljava.core.l6_vm.eval.plugin;

import java.util.UUID;

import com.smalljava.core.l6_vm.vo.VMExpression;

public class NewAddrOperEval implements IEval{

	@Override
	public String eval(VMExpression vo) {
		// 申请得到一个新的内存地址
		// 此地址不可重复，现在采用uuid生成
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		//此地址应当加入到地址表中，作为有效地址判断
		StaticAddrMap.addAddr(uuid);
		return uuid;
	}

}
