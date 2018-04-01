package javaconfig;

import java.math.BigDecimal;

import test.IOrderDao;

public class PaymentActionMixed {
	private ILogger logger =null;
	

	private IOrderDao orderDao;
	
	public PaymentActionMixed(ILogger ilogger) {
		this.logger=ilogger;
	}
	
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
	
	public void addOrder(String orderType) {
		orderDao.addOrder(orderType);
	}
	
}
