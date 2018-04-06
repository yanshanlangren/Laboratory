package com.ibm.ns.schedule.logger;

public interface LogService {

	public void debug(Object caller, Object message, Throwable t);
	
	public void info(Object caller, Object message);
	
	public void error(Object caller, Object message, Throwable t);
	
	public void warn(Object caller, Object message, Throwable t);
}
