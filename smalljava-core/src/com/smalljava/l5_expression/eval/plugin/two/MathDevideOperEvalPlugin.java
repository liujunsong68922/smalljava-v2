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
 * MEMO ִ�г�������
 * 
 * @author liujunsong
 *
 */
public class MathDevideOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(MathDevideOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// ��������
			if (oper.getOpercode().equals("/")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error("��������ʧ�ܣ�����Ϊnull");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error("�����߼������������������Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					intoper.doDevide(rightvar.getVarsvalue());
					return intoper;
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// �ѵڶ����ڵ���ַ�������ȥ
					logger.error("Long���������:" + rightvar.getVarsvalue());
					longoper.doDevide(rightvar.getVarsvalue());
					return longoper;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					logger.error("Float���������:" + rightvar.getVarsvalue());
					floatoper.doDevide(rightvar.getVarsvalue());
					return floatoper;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// �ѵڶ����ڵ���ַ�������ȥ
					logger.error("Double���������:" + rightvar.getVarsvalue());
					doubleoper.doDevide(rightvar.getVarsvalue());
					return doubleoper;
				}
				logger.error("��ERROR���������������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}
}
