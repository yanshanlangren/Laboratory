package test;

import org.springframework.stereotype.Component;

public class LoggerOrderDao implements IOrderDao {

	@Override
	public void updateOrderAfterPayment(String orderId) {
		System.out.println("orderId is "+orderId);
	}

	@Override
	public void addOrder(String orderType) {
		
	}

}
