package com.ibm.ns.schedule;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.ns.schedule.db.DataBaseService;
import com.ibm.ns.schedule.db.MongoDBDataBaseImpl;
import com.ibm.ns.schedule.importer.ImportTask;
import com.ibm.ns.schedule.importer.ImportTaskImpl;
import com.ibm.ns.schedule.logger.LogHandler;
import com.ibm.ns.schedule.logger.LogService;
import com.ibm.ns.schedule.logger.LogUtil;

public class ServiceManager {
	
	private static ServiceManager instance=null;
	
	@Autowired
	private LogService logger; 
	
	private static Map<Class, Object> serviceMap=new HashMap<Class, Object>();
	
	static {
		serviceMap.put(DataBaseService.class, MongoDBDataBaseImpl.class);
		serviceMap.put(ImportTask.class, ImportTaskImpl.class);
	}
	
	public static ServiceManager getServiceManager() {
		if(instance==null)
			instance=new ServiceManager();
		return instance;
	}
	
	private <T> T getLogHandler(Object o) {
		if(null!=o) {
			o=Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new LogHandler(o));
		}
		return (T) o;
	}
	
	public <T> T getService(Class<T> interfaceclass) {
		Object o=serviceMap.get(interfaceclass);
		if(o instanceof Class) {
			try {
				o=((Class) o).newInstance();
				o=getLogHandler(o);
			}catch (Exception e) {
				logger.error(this,"Error creating instance for:" + interfaceclass,e);
				throw new RuntimeException("Error creating instance for:" + interfaceclass, e);
			}
		}
		return (T) o;
	}
	
}
