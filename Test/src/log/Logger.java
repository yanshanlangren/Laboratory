package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	static private File log;
	static private String logPath="C:\\Users\\Elvis\\Desktop\\defaultLog.log";
	static private boolean isInited=false;
	
	public Logger(String logPath){
		this.logPath=logPath;
	}
	
	public static void init() throws IOException{
		if(!isInited){
			log=new File(logPath);
			if(!log.exists())
				log.createNewFile();
			isInited=true;
			if(log.canExecute()){
				return;
			}
			else{
				throw new IOException();
			}
		}
	}
	
	public static void log(String logContent){
		if(!isInited){
			try {
				init();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fos;
		try {
			fos = new FileWriter(log,true);
			String output="["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+"]\t"+logContent+"\r\n";
			fos.write(output);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void log(int logContent){
		log(String.valueOf(logContent));
	}
	
	public static void log(double logContent){
		log(String.valueOf(logContent));
	}
	
	public static void log(float logContent){
		log(String.valueOf(logContent));
	}
	
	public static void main(String[] args){
		Logger logger=new Logger("C:\\Users\\Elvis\\Desktop\\Test.log");
		logger.log("hello World!");
	}
}
