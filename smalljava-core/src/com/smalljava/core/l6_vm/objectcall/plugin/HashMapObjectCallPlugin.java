package com.smalljava.core.l6_vm.objectcall.plugin;

import java.util.HashMap;

import com.smalljava.core.l6_vm.objectcall.IObjectCall;

public class HashMapObjectCallPlugin implements IObjectCall {

	@Override
	public Object objcall(String classname, Object target, String methodname, Object args) {
		// TODO Auto-generated method stub
		// check whether input target is HashMap
		if(! ( target instanceof HashMap)) {
			System.out.print("target object is not HashMap,return null.");
			return null;
		}

		// the following is the method plugin.
		// each method have its own plugin.
		// once matched, then call the plugin to run it.
		if (methodname.equals("size")) {
			return this.size(target);
		}

		System.out.println("Cannot find right methodname:"+methodname+" ,return null.");
		return null;
	}
	
	private int size(Object target) {
		if (target instanceof HashMap) {
			HashMap map1 = (HashMap) target;
			return map1.size();
		}else {
			System.out.println("Error: target object is not HashMap.");
			return 0;
		}
	}

}
