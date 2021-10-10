package com.smalljava.l5_expression.eval.plugin.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smalljava.common.VarValue;
import com.smalljava.l5_expression.eval.ExpressionEval;
import com.smalljava.l5_expression.eval.IExpressionEval;
import com.smalljava.l5_expression.eval.operelement.BooleanValue;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.vartable.IVarTable;

/**
 * MEMO 执行加法运算
 * @author liujunsong
 *
 */
public class LogicOrOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicOrOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// 加法计算
			if (oper.getOpercode().equals("||")) {
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
				if (leftvar.getVartype().equals("boolean")) {
					BooleanValue intoper = new BooleanValue(leftvar.getVarsvalue());
					intoper.doOr(rightvar.getVarsvalue());
					return intoper;
				}
				logger.error("【ERROR】逻辑OR号操作遇到了不支持的数据类型：" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
