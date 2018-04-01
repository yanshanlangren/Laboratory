package test;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javaconfig.ILogger;

public class PaymentAction {
//	private ILogger logger = new FileLogger();
//	private ILogger logger = LoggerFactory.createLogger();
	private ILogger logger =null;
	
	private IOrderDao orderDao;
	
	public PaymentAction(ILogger logger) {
		super();
		this.logger = logger;
	}
	
	@Required
	public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
	
	public void pay(BigDecimal payValue) {
		
		logger.log("pay begin, payValue is "+ payValue);
		
		logger.log("pay end");
	}
	
	public void updateOrderAfterPayment(String orderId) {
        orderDao.updateOrderAfterPayment(orderId);
    }
	
}
