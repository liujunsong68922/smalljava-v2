package com.smalljava.l4_block.blockeval;

//import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smalljava.common.VarValue;
//import com.smalljava.l4_block.SmallJavaBlockConst;
import com.smalljava.l4_block.blockvo.childblock.FORBlock;
import com.smalljava.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.l5_expression.eval.ExpressionEval;
import com.smalljava.l5_expression.vo.RootAST;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class FORBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(FORBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(FORBlock block,L4_HashMapBlockVarTableImpl vartable,IClassTable classtable) throws Exception {
		log("---------->执行for节点");

		//step1:执行开始节点
		//ASTTreeNode node = new ASTTreeNode(block.getBeginNode().getBlockContent(),0);
		RootAST node = expressionASTAnalyse.analyse(block.getBeginNode().getBlockContent());
		//node.analyseTree();
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}

		//boolean b1 = node.eval(block,classtable);
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if(b1 == null) {
			log("【ERROR】IF节点执行begin节点出错."+block.getBeginNode().getBlockContent());
			return false;
		}
		//this.beginNode.execute();
		//step2:执行判断条件
		//logger.error("while 条件表达式:" + this.forcondition);
		//ASTTreeNode node2 = new ASTTreeNode(block.getForconditionNode().getBlockContent(), 0);
		RootAST node2 =expressionASTAnalyse.analyse(block.getForconditionNode().getBlockContent());
		
		//node2.analyseTree();
		if(node2 == null) {
			return false;
		}else {
			node2.show(0);
		}
		//VarTableNode varmap = this.getVarmaps();
		//node.eval(varmap);
		//boolean b2 = node2.eval(block,classtable);
		VarValue b2 = expressionEval.eval(node2, vartable, classtable);
		if(b2 == null) {
			log("【ERROR】IF节点执行condition节点出错."+block.getForconditionNode().getBlockContent());
			return false;
		}
		logger.error("for条件计算结果：" + b2.getVarsvalue());
		
		while (b2.getVarsvalue().equalsIgnoreCase("true")) {
			//step3.执行代码块
			BlockEvaluator be = new BlockEvaluator();
			boolean b3 = be.execute(block.getForloopBlock(),vartable,classtable);
			if(! b3) {
				log("【ERROR】IF节点执行loop块出错."+block.getForloopBlock().getBlockContent());
				return false;
				
			}
			
			//ASTTreeNode node3 = new ASTTreeNode(block.getLoopNode().getBlockContent(), 0);
			RootAST node3 = expressionASTAnalyse.analyse(block.getLoopNode().getBlockContent());
			//node3.analyseTree();
			if(node3 == null) {
				return false;
			}else {
				node3.show(0);
			}
			
			//boolean b4 = node3.eval(block,classtable);
			VarValue b4 = expressionEval.eval(node3, vartable, classtable);
			if(b4 == null) {
				log("【ERROR】执行loop节点出错"+block.getLoopNode().getBlockContent());
				return false;
			}
					
			//重新计算判断条件
			//node2 = new ASTTreeNode(block.getForconditionNode().getBlockContent(), 0);
			node2 =expressionASTAnalyse.analyse(block.getForconditionNode().getBlockContent());
			//node2.analyseTree();
			if(node2 == null) {
				return false;
			}else {
				node2.show(0);
			}
			//boolean b5= node2.eval(block,classtable);
			VarValue b5 =expressionEval.eval(node2, vartable, classtable);
			if(b5 == null) {
				log("【ERROR】IF节点执行condition节点出错."+block.getForconditionNode().getBlockContent());
				return false;				
			}
			
			logger.error("for条件计算结果：" +b5.getVarsvalue());
		}
		
		return true;
	}
	
	private void log(String sinfo) {
		logger.error(sinfo);
	}



}
