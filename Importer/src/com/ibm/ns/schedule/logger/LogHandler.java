package com.ibm.ns.schedule.logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;

public class LogHandler implements InvocationHandler{
	
	private Object o=null;
	
	@Autowired
	private LogService logger; 
	
	public LogHandler(Object o) {
		this.o=o;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj=null;
		try {
			obj=method.invoke(o, args);
		} catch(Exception e) {
			logger.error(this,"Error invoking method:"+method+" in class " + o.getClass(),e);
		}
		return obj;
	}
}
