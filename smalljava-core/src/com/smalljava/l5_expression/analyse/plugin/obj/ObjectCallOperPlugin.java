package com.smalljava.l5_expression.analyse.plugin.obj;

import com.smalljava.l5_expression.analyse.AstOperAndPos;
import com.smalljava.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.l5_expression.vo.AbstractAST;
import com.smalljava.l5_expression.vo.obj.ObjectCallOperElement;

/**
 * MEMO:对象调用元素的识别
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
			// 判断其中有无其他的运算符
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!"};
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// 有任何一个运算符，不处理
				return null;
			} else {
				// 判断变量中是否带有.运算符
				int ipos = strcode.indexOf('.');
				if (ipos < 0 ) {
					return null;
				} else {
					//定义这是一个对象调用
					ObjectCallOperElement objcall = new ObjectCallOperElement();
					//TODO:定义填充Objectcall的内容
					return objcall;
				}
			}

		}
		return null;
	}

}
