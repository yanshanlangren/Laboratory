package com.ibm.ns.schedule.importer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.ns.schedule.Constants;

public class ProjectServletContextListner implements ServletContextListener {

    private static java.util.Timer timer = null;
    
    private static WebApplicationContext ctx;
    
    @Autowired
    private TaskController taskController;
    
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		  timer.cancel();  
		  event.getServletContext().log("Scheduler task destroying...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
        timer = new java.util.Timer(true);  
        ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
        taskController=ctx.getBean(TaskController.class);
//        ctx.log("Initiating Schedule Task");  
        timer.schedule(taskController,   //   Task Factory
                         0,             //   Start delay  
                         Constants.IMPORT_TASK_INTERVAL*1000        //   Try interval 
                       );  
	}
}
