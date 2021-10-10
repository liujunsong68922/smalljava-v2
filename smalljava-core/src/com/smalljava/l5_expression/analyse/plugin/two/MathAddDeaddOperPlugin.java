package com.smalljava.l5_expression.analyse.plugin.two;

import com.smalljava.l5_expression.analyse.AstOperAndPos;
import com.smalljava.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.l5_expression.vo.AbstractAST;
import com.smalljava.l5_expression.vo.MiddleAST;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l5_expression.vo.two.DualOperDataOperElement;

/**
 * MEMO：加号，减号分析插件
 * 
 * @author liujunsong
 *
 */
public class MathAddDeaddOperPlugin extends DefaultIPluginImplement{

	/**
	 * MEMO加法减法分析器
	 */
	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] {"+","-"};
		AstOperAndPos oap = getLastOperCode(strcode,opers);
		
		if(oap==null) {
			//查找加号，减号失败
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","!","==",">=","<=",">","<","="};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		if(highoap!=null) {
			//存在计算优先级更低的运算符，返回
			return null;
		}
		
		//生成新对象准备返回
		//使用一个二元运算符元素来进行封装
		DualOperDataOperElement root = new DualOperDataOperElement();
		//记录操作符
		root.setOpercode(oap.getOpercode());
		
		//从操作符开始切割字符串为两部分
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());

		//leftstring = trimReturnAndSpace(leftstring);
		if(leftstring.equals("")) {
			//左操作数不可为空
			return null;
		}else {
			//判断结束字符串的合法性
			char lastchar1 = leftstring.charAt(leftstring.length()-1);
			if((lastchar1>='a' && lastchar1<='z') 
				|| (lastchar1>='A' && lastchar1<='Z')
				|| (lastchar1>='0' && lastchar1<='9')
				|| (lastchar1==')')
				|| (lastchar1=='\"') 
				|| (lastchar1==' ')){
				//这些是合法字符串，通过
			}else {
				//左操作数结束不合法，不处理
				return null;
			}
		}
		//检查右操作数是否合法
		if(rightString.equals("")) {
			//左操作数不可为空
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar>='0' && firstchar<='9')
					|| (firstchar=='(')
					|| (firstchar=='\"') 
					|| (firstchar==' ')){
					//这些是合法字符串，通过
				}else {
					//左操作数结束不合法，不处理
					return null;
				}
		}
		
		
		RootAST leftelement = new MiddleAST();
		RootAST rightelement = new MiddleAST();
		leftelement.setStrexpression(leftstring);
		rightelement.setStrexpression(rightString);
		
		
		root.getChildren().add(leftelement);
		root.getChildren().add(rightelement);
		
		//分析结束，返回之
		return root;
	}

}