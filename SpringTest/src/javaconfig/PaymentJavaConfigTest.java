package javaconfig;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PaymentJavaConfig.class)
public class PaymentJavaConfigTest {

    @Autowired
    private PaymentActionMixed paymentActionMixed;

    @Test
    public void testPaymentMixedAddOrder() {
        paymentActionMixed.pay(new BigDecimal(100));
    }
}