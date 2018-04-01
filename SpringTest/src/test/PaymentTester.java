package test;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PaymentTester {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		PaymentAction paymentAction=(PaymentAction)context.getBean("paymentAction");
		paymentAction.updateOrderAfterPayment("123123");
	}

}
