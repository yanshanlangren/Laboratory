package com.ibm.ns.schedule.importer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ibm.ns.schedule.Constants;

public class ProjectServletContextListner implements ServletContextListener {

	//��ʱ��   
    private static java.util.Timer timer = null; 
    
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		  timer.cancel();  
		  event.getServletContext().log("Scheduler task destroying...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
        timer = new java.util.Timer(true);  
        javax.servlet.ServletContext ctx = event.getServletContext();  
        ctx.log("Initiating Schedule Task");  
        /*  
        long   period   =   Long.valueOf((String)ctx.getInitParameter("period")).longValue();  
        */  
 
        timer.schedule(new TaskController(),   //   Task Factory
                         0,             //   Start delay  
                         Constants.IMPORT_TASK_INTERVAL*1000        //   Try interval 
                       );  
	}
}
