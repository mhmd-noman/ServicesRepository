package order.management.services.beans;

import java.util.List;

import common.beans.Order;
import common.enums.OrderManagementServiceAction;

public class OrderManagementRequest {
	private List<Integer> orderIds = null;
	private Order order = null;
	private Integer pageNo = null;
	private Integer pageSize = null;
	private OrderManagementServiceAction orderManagementServiceAction = null;
	public List<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
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
	public OrderManagementServiceAction getOrderManagementServiceAction() {
		return orderManagementServiceAction;
	}
	public void setOrderManagementServiceAction(OrderManagementServiceAction orderManagementServiceAction) {
		this.orderManagementServiceAction = orderManagementServiceAction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderManagementRequest [orderIds=");
		builder.append(orderIds);
		builder.append(", order=");
		builder.append(order);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", orderManagementServiceAction=");
		builder.append(orderManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
