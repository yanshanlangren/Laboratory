package test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses= {IOrderDao.class,PaymentAction.class})
public class PaymentConfig {
	
}
