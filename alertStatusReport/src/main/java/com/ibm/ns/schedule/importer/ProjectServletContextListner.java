package com.ibm.ns.schedule.importer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ProjectServletContextListner implements ServletContextListener {

    private static java.util.Timer timer = null; 
    
    private ApplicationContext ctx;
    
    private String IMPORT_TASK_INTERVAL="10";
    
    @Value("#{prop.IMPORT_TASK_INTERVAL}")
    public void setIMPORT_TASK_INTERVAL(String import_task_interval) {
    	this.IMPORT_TASK_INTERVAL=import_task_interval;
    }
    
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		  timer.cancel();  
		  event.getServletContext().log("Scheduler task destroying...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
        timer = new java.util.Timer(true);  
        ctx=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        TaskController tc=ctx.getBean(TaskController.class);
//        javax.servlet.ServletContext ctx = event.getServletContext();  
//        ctx.log("Initiating Schedule Task");  
        /*  
        long   period   =   Long.valueOf((String)ctx.getInitParameter("period")).longValue();  
        */  
 
        timer.schedule(tc,   //   Task Factory
                         0,             //   Start delay  
                         Integer.valueOf(IMPORT_TASK_INTERVAL)*1000        //   Try interval 
                       );  
	}
}
