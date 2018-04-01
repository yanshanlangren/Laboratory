package test;

import javaconfig.ILogger;

public class LoggerFactory {

	public static ILogger createLogger() {
		return new FileLogger();
	}
}
