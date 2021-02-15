package invoice.generation.service.beans;

import java.util.List;

import common.beans.Order;

public class InvoiceGenerationResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private String invoiceId = null;
	private List<Order> orders = null;

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
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", invoiceId=");
		builder.append(invoiceId);
		builder.append(", orders=");
		builder.append(orders);
		return builder.toString();
	}
}
