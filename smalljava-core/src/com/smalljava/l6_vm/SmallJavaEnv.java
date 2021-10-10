package com.smalljava.l6_vm;

import java.util.HashMap;

import com.smalljava.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.classtable.impl.ClassTableImpl;

/**
 * MEMO:这是一个SmallJava的运行环境，每个运行环境有自己的ClassTable定义
 * MEMO:SmallJavaEnvirnoment里面同时保存了所读入类的静态变量
 * MEMO:因此上每个JavaFile实际上对应生成一个自己的Envirnoment
 * @author liujunsong
 *
 */
public class SmallJavaEnv {
	/**
	 * MEMO:classtable包装在SmallJavaEnv里面定义
	 */
	private IClassTable classtable ;
	/**
	 * MEMO:记录JavaFile字符串和对应解析出来的VO对象，避免重复计算
	 */
	private HashMap<String,JavaFileRootVO> filemap;
	/**
	 * MEMO:空白的构造函数
	 */
	public SmallJavaEnv() {
		this.classtable = new ClassTableImpl();
	}
	public IClassTable getClasstable() {
		return classtable;
	}
	public void setClasstable(IClassTable classtable) {
		this.classtable = classtable;
	}
	
	/**
	 * MEMO：读取字符串，利用javafile进行解析，将解析结果写入hashmap里面去
	 * @param strjavafile
	 * @return true,加载/解析成功；false:加载/解析失败
	 */
	public boolean loadJavaFile(String strjavafile) {
		
		//如果这个字符串已经在里面则不重复加载
		if(this.filemap.containsKey(strjavafile)) {
			return true;
		}
		
		//step1:加载Java文件
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		JavaFileRootVO rootvo = jfa.analyse(strjavafile);
		if(rootvo != null) {
			System.out.println("----->JAVA FILE 分析执行成功！");
			rootvo.show();
			this.filemap.put(strjavafile, rootvo);
		}else {
			System.out.println("----->JAVA FILE 分析执行失败！");
			return false;
		}
		
		//step2.利用ClassTable分析工具来处理ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			System.out.println("ClassTable 分析成功");
			this.setClasstable(classtable);
			return true;
		}else {
			System.out.println("ClassTable 分析失败");
			return false;
		}		
	}
}
