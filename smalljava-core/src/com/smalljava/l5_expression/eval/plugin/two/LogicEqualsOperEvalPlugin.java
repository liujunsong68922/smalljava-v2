package com.smalljava.l5_expression.eval.plugin.two;

import com.smalljava.common.VarValue;
import com.smalljava.common.logging.Logger;
import com.smalljava.common.logging.LoggerFactory;
import com.smalljava.l5_expression.eval.ExpressionEval;
import com.smalljava.l5_expression.eval.IExpressionEval;
import com.smalljava.l5_expression.eval.operelement.DoubleValue;
import com.smalljava.l5_expression.eval.operelement.FloatValue;
import com.smalljava.l5_expression.eval.operelement.IntegerValue;
import com.smalljava.l5_expression.eval.operelement.LongValue;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.vartable.IVarTable;

/**
 * MEMO 执行加法运算
 * @author liujunsong
 *
 */
public class LogicEqualsOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicEqualsOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// 加法计算
			if (oper.getOpercode().equals("==")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// 生成一个新的评估器
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error("加法计算失败，参数为null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error("程序逻辑错误，左操作对象类型为null");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					intoper.doequals(rightvar.getVarsvalue());
					return intoper;
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// 把第二个节点的字符串传进去
					logger.error("Long右面操作数:" + rightvar.getVarsvalue());
					longoper.doequals(rightvar.getVarsvalue());
					return longoper;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					logger.error("Float右面操作数:" + rightvar.getVarsvalue());
					floatoper.doequals(rightvar.getVarsvalue());
					return floatoper;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// 把第二个节点的字符串传进去
					logger.error("Double右面操作数:" + rightvar.getVarsvalue());
					doubleoper.doequals(rightvar.getVarsvalue());
					return doubleoper;
				}
				logger.error("【ERROR】GE操作遇到了不支持的数据类型：" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
