package order.management.services.beans;

import common.beans.Order;
import common.enums.OrderManagementServiceAction;

public class OrderManagementRequest {
	private Order order = null;
	private OrderManagementServiceAction orderManagementServiceAction = null;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderManagementServiceAction getOrderManagementServiceAction() {
		return orderManagementServiceAction;
	}
	public void setOrderManagementServiceAction(OrderManagementServiceAction orderManagementServiceAction) {
		this.orderManagementServiceAction = orderManagementServiceAction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderManagementRequest [order=");
		builder.append(order);
		builder.append(", orderManagementServiceAction=");
		builder.append(orderManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
