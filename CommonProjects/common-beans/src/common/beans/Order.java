package common.beans;

import java.util.Date;
import java.util.List;

public class Order {
	private Integer orderId = null;
	private String orderDescription = null;
	private String custName = null;
	private String custPhone = null;
	private String custPhone2 = null;
	private String custAddress2 = null;
	private String custAddress1 = null;
	private String custEmail = null;
	private String area = null;
	private String city = null;
	private String state = null;
	private String country = null;
	private Double orderPrchaseAmount = null;
	private Double orderOrgAmount = null;
	private Double orderFromOrgAmount = null;
	private Double orderToOrgAmount = null;
	private Double orderRtlAmount = null;
	private Double orderFromRtlAmount = null;
	private Double orderToRtlAmount = null;
	private Double orderCalcDiscount = null;
	private Double orderFromCalcDiscount = null;
	private Double orderToCalcDiscount = null;
	private Double profit = null;
	private Date createdOn = null;
	private Date cancelledAt = null;
	private String orderStatus = null;
	private List<Integer> orderIds = null;
	private List<Product> orderedProducts = null;

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustPhone2() {
		return custPhone2;
	}
	public void setCustPhone2(String custPhone2) {
		this.custPhone2 = custPhone2;
	}
	public String getCustAddress2() {
		return custAddress2;
	}
	public void setCustAddress2(String custAddress2) {
		this.custAddress2 = custAddress2;
	}
	public String getCustAddress1() {
		return custAddress1;
	}
	public void setCustAddress1(String custAddress1) {
		this.custAddress1 = custAddress1;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getOrderPrchaseAmount() {
		return orderPrchaseAmount;
	}
	public void setOrderPrchaseAmount(Double orderPrchaseAmount) {
		this.orderPrchaseAmount = orderPrchaseAmount;
	}
	public Double getOrderOrgAmount() {
		return orderOrgAmount;
	}
	public void setOrderOrgAmount(Double orderOrgAmount) {
		this.orderOrgAmount = orderOrgAmount;
	}
	public Double getOrderFromOrgAmount() {
		return orderFromOrgAmount;
	}
	public void setOrderFromOrgAmount(Double orderFromOrgAmount) {
		this.orderFromOrgAmount = orderFromOrgAmount;
	}
	public Double getOrderToOrgAmount() {
		return orderToOrgAmount;
	}
	public void setOrderToOrgAmount(Double orderToOrgAmount) {
		this.orderToOrgAmount = orderToOrgAmount;
	}
	public Double getOrderRtlAmount() {
		return orderRtlAmount;
	}
	public void setOrderRtlAmount(Double orderRtlAmount) {
		this.orderRtlAmount = orderRtlAmount;
	}
	public Double getOrderFromRtlAmount() {
		return orderFromRtlAmount;
	}
	public void setOrderFromRtlAmount(Double orderFromRtlAmount) {
		this.orderFromRtlAmount = orderFromRtlAmount;
	}
	public Double getOrderToRtlAmount() {
		return orderToRtlAmount;
	}
	public void setOrderToRtlAmount(Double orderToRtlAmount) {
		this.orderToRtlAmount = orderToRtlAmount;
	}
	public Double getOrderCalcDiscount() {
		return orderCalcDiscount;
	}
	public void setOrderCalcDiscount(Double orderCalcDiscount) {
		this.orderCalcDiscount = orderCalcDiscount;
	}
	public Double getOrderFromCalcDiscount() {
		return orderFromCalcDiscount;
	}
	public void setOrderFromCalcDiscount(Double orderFromCalcDiscount) {
		this.orderFromCalcDiscount = orderFromCalcDiscount;
	}
	public Double getOrderToCalcDiscount() {
		return orderToCalcDiscount;
	}
	public void setOrderToCalcDiscount(Double orderToCalcDiscount) {
		this.orderToCalcDiscount = orderToCalcDiscount;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getCancelledAt() {
		return cancelledAt;
	}
	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", orderDescription=");
		builder.append(orderDescription);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", custPhone=");
		builder.append(custPhone);
		builder.append(", custPhone2=");
		builder.append(custPhone2);
		builder.append(", custAddress2=");
		builder.append(custAddress2);
		builder.append(", custAddress1=");
		builder.append(custAddress1);
		builder.append(", custEmail=");
		builder.append(custEmail);
		builder.append(", area=");
		builder.append(area);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", country=");
		builder.append(country);
		builder.append(", orderPrchaseAmount=");
		builder.append(orderPrchaseAmount);
		builder.append(", orderOrgAmount=");
		builder.append(orderOrgAmount);
		builder.append(", orderRtlAmount=");
		builder.append(orderRtlAmount);
		builder.append(", orderCalcDiscount=");
		builder.append(orderCalcDiscount);
		builder.append(", orderFromCalcDiscount=");
		builder.append(orderFromCalcDiscount);
		builder.append(", orderToCalcDiscount=");
		builder.append(orderToCalcDiscount);
		builder.append(", profit=");
		builder.append(profit);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", cancelledAt=");
		builder.append(cancelledAt);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", orderIds=");
		builder.append(orderIds);
		builder.append(", orderedProducts=");
		builder.append(orderedProducts);
		builder.append("]");
		return builder.toString();
	}
}
