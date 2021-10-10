package com.smalljava.l5_expression.eval.plugin.obj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import com.smalljava.common.UuidObjectManager;
import com.smalljava.common.VarValue;
import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;
import com.smalljava.l5_expression.eval.ExpressionEval;
import com.smalljava.l5_expression.eval.IExpressionEval;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l5_expression.vo.obj.ObjectCallOperElement;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.vartable.IVarTable;

/**
 * Java对象方法调用的执行插件
 * 
 * @author liujunsong
 *
 */
public class ObjectCallEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ObjectCallEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null) {
			logger.error("root is null");
			return null;
		}

		if (!(root instanceof ObjectCallOperElement)) {
			// call argument error.
			logger.error("root is not ObjectCallOperElement");
			return null;
		} else {
			// 暂时不考虑自定义class的内容
			// 自定义class的部分需要增加新的代码进行处理
			// 目前只考虑java虚拟机已经支持的类的处理
			ObjectCallOperElement objcall = (ObjectCallOperElement) root;
			String objname = objcall.getObjname();
			logger.info("objname:" + objname);

			// 首先访问变量表，获取对象VarValue
			VarValue objvar = vartable.getVarValue(objname);
			if (objvar == null) {
				logger.error("[ERROR] find var in vartable is null:" + objname);
				return null;
			}
			String objuuid = objvar.getVarsvalue();
			Object targetobj = UuidObjectManager.getObject(objuuid);
			if (targetobj == null) {
				// 此处程序执行发生了错误
				logger.error("[ERROR]targetobj is null object.");
				return null;
			}

			// 将子节点经过计算转换成callarg
			ArrayList<VarValue> arglist = new ArrayList<VarValue>();
			for (RootAST child : root.getChildren()) {
				ExpressionEval eval = new ExpressionEval();
				VarValue vvalue1 = eval.eval(child, vartable, classtable);
				logger.info("child return:" + vvalue1.toJSONString());

				// 将返回值写入callarg
				arglist.add(vvalue1);
			}
			return this.objectcall(targetobj, objname, arglist);

			// logger.error("[ERROR] object call error happened.");
			// return null;
		}
	}

	public VarValue objectcall(Object targobj, String methodname, ArrayList<VarValue> arglist) {

		// step1. get Method
		int argnum = arglist.size();
		Method mm = this.getMethod(targobj, methodname,argnum);

		if (mm == null) {
			logger.error("[ERROR] get targetobj's methodname failed." + methodname);
		}
		// Step2. call objects by method

		Object args = null;
		Object retobj;
		try {
			retobj = mm.invoke(targobj, args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if (retobj instanceof Integer) {
			VarValue var1 = new VarValue();
			var1.setVarname("");
			var1.setVartype("int");
			var1.setVarsvalue("" + retobj.toString());
			return var1;
		} else if (retobj instanceof Long) {
			VarValue var2 = new VarValue();
			var2.setVarname("");
			var2.setVartype("long");
			var2.setVarsvalue("" + retobj.toString());
			return var2;
		} else if (retobj instanceof Float) {
			VarValue var3 = new VarValue();
			var3.setVarname("");
			var3.setVartype("float");
			var3.setVarsvalue("" + retobj.toString());
			return var3;
		} else if (retobj instanceof Double) {
			VarValue var4 = new VarValue();
			var4.setVarname("");
			var4.setVartype("double");
			var4.setVarsvalue("" + retobj.toString());
			return var4;
		} else if (retobj instanceof String) {
			VarValue var4 = new VarValue();
			var4.setVarname("");
			var4.setVartype("String");
			var4.setVarsvalue("" + retobj.toString());
			return var4;
		} else if (retobj instanceof Object) {
			// create new uuid
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			UuidObjectManager uuidmanager = new UuidObjectManager();
			uuidmanager.setObject(uuid, retobj);

			VarValue var5 = new VarValue();
			var5.setVarname("");
			var5.setVartype("Object");
			var5.setVarsvalue(uuid);
			return var5;
		}

		return null;
	}

	/**
	 * MEMO：利用JAVA的反射机制，从Object里面获取第一个符合的Method回来。 MEMO:
	 * 这一方法需要用到Java语言的特定特性，因此不能直接迁移到其他语言。 MEMO: 暂时不考虑对象对用的重载方法，同名方法调用再考虑进行转换支持
	 * 
	 * @param obj
	 * @param methodname
	 * @param argnum 
	 * @return
	 */
	private Method getMethod(Object obj, String methodname, int argnum) {
		// TODO: convert object to Method
		Method methods[] = obj.getClass().getMethods();
		for(Method m1 : methods) {
			if(! m1.getName().equals(methodname)) {
				continue;
			}
			//method name match
			logger.info("method name match."+methodname);
			//这里只进行一个基本的判断，就是参数的数量
			//暂时不考虑进行数据类型的额外判断
			if(m1.getParameterCount() == argnum) {
				logger.info("find argnum match:"+argnum);
				return m1;
			}
		}
		logger.info("Cannot find match method.");
		return null;
	}
}
