package annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses= {IOrderDao.class,PaymentActionMixed.class})
public class PaymentConfig {

}
