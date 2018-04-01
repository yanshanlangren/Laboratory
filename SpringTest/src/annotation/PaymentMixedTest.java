package annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PaymentConfig.class)
public class PaymentMixedTest {
	
	@Autowired
	private PaymentActionMixed paymentActionMixed;
	
	@Test
	public void testPaymentMixedAddOrder() {
		paymentActionMixed.addOrder("create_sub");
	}
}
