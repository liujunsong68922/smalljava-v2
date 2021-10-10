package com.smalljava.l6_vm;

import java.util.HashMap;

import com.smalljava.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.l9_space.classtable.IClassTable;
import com.smalljava.l9_space.classtable.impl.ClassTableImpl;

/**
 * MEMO:����һ��SmallJava�����л�����ÿ�����л������Լ���ClassTable����
 * MEMO:SmallJavaEnvirnoment����ͬʱ��������������ľ�̬����
 * MEMO:�����ÿ��JavaFileʵ���϶�Ӧ����һ���Լ���Envirnoment
 * @author liujunsong
 *
 */
public class SmallJavaEnv {
	/**
	 * MEMO:classtable��װ��SmallJavaEnv���涨��
	 */
	private IClassTable classtable ;
	/**
	 * MEMO:��¼JavaFile�ַ����Ͷ�Ӧ����������VO���󣬱����ظ�����
	 */
	private HashMap<String,JavaFileRootVO> filemap;
	/**
	 * MEMO:�հ׵Ĺ��캯��
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
	 * MEMO����ȡ�ַ���������javafile���н��������������д��hashmap����ȥ
	 * @param strjavafile
	 * @return true,����/�����ɹ���false:����/����ʧ��
	 */
	public boolean loadJavaFile(String strjavafile) {
		
		//�������ַ����Ѿ����������ظ�����
		if(this.filemap.containsKey(strjavafile)) {
			return true;
		}
		
		//step1:����Java�ļ�
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		JavaFileRootVO rootvo = jfa.analyse(strjavafile);
		if(rootvo != null) {
			System.out.println("----->JAVA FILE ����ִ�гɹ���");
			rootvo.show();
			this.filemap.put(strjavafile, rootvo);
		}else {
			System.out.println("----->JAVA FILE ����ִ��ʧ�ܣ�");
			return false;
		}
		
		//step2.����ClassTable��������������ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			System.out.println("ClassTable �����ɹ�");
			this.setClasstable(classtable);
			return true;
		}else {
			System.out.println("ClassTable ����ʧ��");
			return false;
		}		
	}
}
