package order.management.services.beans;

import java.util.List;

import common.beans.Order;

public class OrderManagementResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private List<Order> ordersList = null;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public List<Order> getOrders() {
		return ordersList;
	}
	public void setOrders(List<Order> ordersList) {
		this.ordersList = ordersList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductsManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", ordersList=");
		builder.append(ordersList);
		builder.append("]");
		return builder.toString();
	}
}
