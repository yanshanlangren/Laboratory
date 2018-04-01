package annotation3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppClassPathXML {

	public static void main(String[] args) {
		
		ApplicationContext context= new ClassPathXmlApplicationContext("/Annotation3.xml");
		CarService service=context.getBean(CarService.class);
		service.addCar("BMW");
	}

}
