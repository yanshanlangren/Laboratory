package com.ibm.ns.schedule.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogUtil implements LogService{

	public void debug(Object caller, Object message, Throwable t) {
		log(caller, Level.DEBUG, message, t);
	}
	
	public void info(Object caller, Object message) {
		log(caller, Level.INFO, message, null);
	}

	public void error(Object caller, Object message, Throwable t) {
		log(caller, Level.ERROR, message, t);
	}
	
	public void warn(Object caller, Object message, Throwable t) {
		log(caller, Level.WARN, message, t);
	}
	
	private static void log(Object caller, Level level, Object message, Throwable t) {
		getLogger(caller).log(level, message,t);
	}

	private static Logger getLogger(Object caller) {
//		Logger logger=Logger.getLogger();
		Logger logger = Logger.getLogger(LoggerTester.class);  
		if(caller!=null) {
			if(caller instanceof Class)
				logger=Logger.getLogger((Class <?>)caller);
			else
				logger=Logger.getLogger(caller.getClass());
		}
		return logger;
	}

	public static void main(String[] args) {
//		LogUtil.info(LogUtil.class, "this is from info");
//		
//		LogUtil.warn(LogUtil.class, "this is from warn",new IOException());		
//
//		LogUtil.error(LogUtil.class, "this is from error",new Exception());
	}
}
