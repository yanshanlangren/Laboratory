package test;

import javaconfig.ILogger;

public class FileLogger implements ILogger {

	@Override
	public void log(String str) {
		System.out.println(str);
	}

}
