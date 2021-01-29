package order.management.services.beans;

import common.beans.Order;
import common.enums.OrderManagementServiceAction;

public class OrderManagementRequest {
	private Order order = null;
	private Integer pageNo = null;
	private Integer pageSize = null;
	private boolean fetchCompletedOrdersOnly = false;
	private boolean fetchCencelledOrdersOnly = false;
	private boolean fetchInProgressOrdersOnly = false;
	private OrderManagementServiceAction orderManagementServiceAction = null;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isFetchCompletedOrdersOnly() {
		return fetchCompletedOrdersOnly;
	}
	public void setFetchCompletedOrdersOnly(boolean fetchCompletedOrdersOnly) {
		this.fetchCompletedOrdersOnly = fetchCompletedOrdersOnly;
	}
	public boolean isFetchCencelledOrdersOnly() {
		return fetchCencelledOrdersOnly;
	}
	public void setFetchCencelledOrdersOnly(boolean fetchCencelledOrdersOnly) {
		this.fetchCencelledOrdersOnly = fetchCencelledOrdersOnly;
	}
	public boolean isFetchInProgressOrdersOnly() {
		return fetchInProgressOrdersOnly;
	}
	public void setFetchInProgressOrdersOnly(boolean fetchInProgressOrdersOnly) {
		this.fetchInProgressOrdersOnly = fetchInProgressOrdersOnly;
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
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", fetchCompletedOrdersOnly=");
		builder.append(fetchCompletedOrdersOnly);
		builder.append(", fetchCencelledOrdersOnly=");
		builder.append(fetchCencelledOrdersOnly);
		builder.append(", fetchInProgressOrdersOnly=");
		builder.append(fetchInProgressOrdersOnly);
		builder.append(", orderManagementServiceAction=");
		builder.append(orderManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
