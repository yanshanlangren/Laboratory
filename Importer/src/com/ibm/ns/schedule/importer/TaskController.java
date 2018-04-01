package com.ibm.ns.schedule.importer;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.ns.schedule.ServiceManager;
import com.ibm.ns.schedule.logger.LogService;
import com.ibm.ns.schedule.logger.LogUtil;

public class TaskController extends TimerTask {

    private static boolean isRunning  = false;   
    private static int t = 3;
    private ImportTask ct = ServiceManager.getServiceManager().getService(ImportTask.class);   
    
	@Autowired
	private LogService logger; 
    
    @Override
    public void run() {
        if(!isRunning){   
            if(ct != null){  
                isRunning = true;   
                ct.execute();        
                isRunning = false;  
            }else{  
                if(t == 0)   
                	return;  
                t--;  
                logger.error(this, "[com.ibm.ns.schedule.NewTask]  The   task   is   null.", new Exception());
            }  
        }else{  
            logger.info(this, "The   task   is   running.");
        }   

    }

}