package test;

public interface IOrderDao {
	public void updateOrderAfterPayment(String orderId);

	public void addOrder(String orderType);
}
