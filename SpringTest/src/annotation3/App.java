package annotation3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext = new AnnotationConfigApplicationContext("annotation3");
        CarService service = appContext.getBean(CarService.class);
        service.addCar("宝马");
    }
}