package com.smalljava.core.l6_vm.eval.plugin;

import java.util.UUID;

import com.smalljava.core.l6_vm.vo.VMExpression;

public class NewAddrOperEval implements IEval{

	@Override
	public String eval(VMExpression vo) {
		// ����õ�һ���µ��ڴ��ַ
		// �˵�ַ�����ظ������ڲ���uuid����
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		//�˵�ַӦ�����뵽��ַ���У���Ϊ��Ч��ַ�ж�
		StaticAddrMap.addAddr(uuid);
		return uuid;
	}

}
