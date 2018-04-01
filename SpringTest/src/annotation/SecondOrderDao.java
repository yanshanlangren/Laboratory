package annotation;

import org.springframework.stereotype.Component;

@Component
public class SecondOrderDao implements IOrderDao{

	@Override
	public void updateOrderAfterPayment(String orderId) {
		System.out.println("SecondOrderDao: updateOrderAfterPayment "+orderId);
	}

	@Override
	public void addOrder(String orderType) {
		System.out.println("SecondOrderDao: addOrder "+orderType);
	}

}
