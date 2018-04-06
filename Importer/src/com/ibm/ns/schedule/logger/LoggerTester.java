package com.ibm.ns.schedule.logger;

import org.apache.log4j.Logger;

public class LoggerTester {
	private static Logger logger = Logger.getLogger(LoggerTester.class);  
	
    public static void main(String[] args) {  
        // System.out.println("This is println message.");  

        logger.debug("This is debug message.");  
        logger.info("This is info message.");  
        logger.error("This is error message.");  
    }  
}
