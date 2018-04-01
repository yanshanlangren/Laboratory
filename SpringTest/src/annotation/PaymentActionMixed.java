package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentActionMixed {

	@Autowired
	private IOrderDao orderDao;
	
	public PaymentActionMixed() {
		super();
	}
	
	public void addOrder(String orderType) {
		orderDao.addOrder(orderType);
	}
	
}
