package com.smalljava.l5_expression.analyse.plugin.obj;

import com.smalljava.l5_expression.analyse.AstOperAndPos;
import com.smalljava.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.l5_expression.vo.AbstractAST;
import com.smalljava.l5_expression.vo.obj.ObjectCallOperElement;

/**
 * MEMO:�������Ԫ�ص�ʶ��
 * 
 * @author liujunsong
 *
 */
public class ObjectCallOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// �ж��������������������
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!"};
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// ���κ�һ���������������
				return null;
			} else {
				// �жϱ������Ƿ����.�����
				int ipos = strcode.indexOf('.');
				if (ipos < 0 ) {
					return null;
				} else {
					//��������һ���������
					ObjectCallOperElement objcall = new ObjectCallOperElement();
					//TODO:�������Objectcall������
					return objcall;
				}
			}

		}
		return null;
	}

}
