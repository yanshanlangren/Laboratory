package javaconfig;

public class ConsoleLogger implements ILogger{

	@Override
	public void log(String str) {
		System.out.println("ConsoleLogger log: "+ str);
	}

}
