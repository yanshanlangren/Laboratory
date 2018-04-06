package scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Scheduler implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext context=new AnnotationConfigApplicationContext("");
	}
	
}
