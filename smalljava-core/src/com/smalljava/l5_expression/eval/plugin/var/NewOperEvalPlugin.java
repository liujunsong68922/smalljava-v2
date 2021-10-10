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
		//null值检查
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			//调用classtable来获取class
			Class class1 = classtable.getClass(classname);
			if(class1 == null) {
				logger.error("【ERROR】 classname not found."+classname);
				return null;
			}else {
				try {
					//利用类来创建对象
					Object obj1 = class1.newInstance();
					//同时生成一个唯一uuid，代表其引用值
					String uuid = UUID.randomUUID().toString().replaceAll("-","");
					//对象与uuid压入公共对象映射表中
					UuidObjectManager.setObject(uuid, obj1);
					//构建返回对象
					VarValue vvalue = new VarValue();
					vvalue.setVartype(classname);
					vvalue.setVarsvalue(uuid);
					return vvalue;
				} catch (Exception e) {
					logger.error("【ERROR】error when create new instance");
					e.printStackTrace();
					return null;
				} 
			}
		}
		return null;
	}

}
