package com.smalljava.l5_expression.eval.plugin.var;

import java.util.UUID;

import com.smalljava.common.UuidObjectManager;
import com.smalljava.common.VarValue;
import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;
import com.smalljava.l5_expression.eval.IExpressionEval;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l5_expression.vo.var.NewOperElement;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.vartable.IVarTable;

public class NewOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(NewOperEvalPlugin.class);

	@SuppressWarnings("rawtypes")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//nullֵ���
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			//����classtable����ȡclass
			Class class1 = classtable.getClass(classname);
			if(class1 == null) {
				logger.error("��ERROR�� classname not found."+classname);
				return null;
			}else {
				try {
					//����������������
					Object obj1 = class1.newInstance();
					//ͬʱ����һ��Ψһuuid������������ֵ
					String uuid = UUID.randomUUID().toString().replaceAll("-","");
					//������uuidѹ�빫������ӳ�����
					UuidObjectManager.setObject(uuid, obj1);
					//�������ض���
					VarValue vvalue = new VarValue();
					vvalue.setVartype(classname);
					vvalue.setVarsvalue(uuid);
					return vvalue;
				} catch (Exception e) {
					logger.error("��ERROR��error when create new instance");
					e.printStackTrace();
					return null;
				} 
			}
		}
		return null;
	}

}
