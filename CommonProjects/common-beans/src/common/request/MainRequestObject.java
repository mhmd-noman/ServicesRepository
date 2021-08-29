package common.request;

import java.util.List;

import common.beans.Invoice;
import common.beans.Order;
import common.beans.Product;
import common.beans.Query;
import common.beans.User;
import common.beans.Wishlist;
import common.enums.UserManagementServiceAction;

public class MainRequestObject {
	private Integer serialNumber = 0;
	private String firstName = null;
	private String lastName = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String phoneNumber = null;
	private String dbCode = null;
	private String serviceId = null;
	private Integer pageNo = null;
	private Integer pageSize = null;
	private User userInfo = null;
	private Order orderInfo = null;
	private Product productInfo = null;
	private Query queryInfo = null;
	private Wishlist wishlist = null;
	private Invoice invoiceInfo = null;
	private List<Integer> ids = null;
	private boolean returnCount = false;
	private boolean fetchOutOfStockProducts = false;
	private boolean fetchProductsWithDiscountOnly = false;
	String enabled = null;
	private UserManagementServiceAction userManagementServiceAction = null;

	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public UserManagementServiceAction getUserManagementServiceAction() {
		return userManagementServiceAction;
	}
	public void setUserManagementServiceAction(UserManagementServiceAction userManagementServiceAction) {
		this.userManagementServiceAction = userManagementServiceAction;
	}
	public String getDbCode() {
		return dbCode;
	}
	public void setDbCode(String dbCode) {
		this.dbCode = dbCode;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public Order getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(Order orderInfo) {
		this.orderInfo = orderInfo;
	}
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	public Product getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(Product productInfo) {
		this.productInfo = productInfo;
	}
	public Query getQueryInfo() {
		return queryInfo;
	}
	public void setQueryInfo(Query queryInfo) {
		this.queryInfo = queryInfo;
	}
	public Wishlist getWishlist() {
		return wishlist;
	}
	public void setWishlist(Wishlist wislist) {
		this.wishlist = wislist;
	}
	public Invoice getInvoiceInfo() {
		return invoiceInfo;
	}
	public void setInvoiceInfo(Invoice invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public boolean isReturnCount() {
		return returnCount;
	}
	public void setReturnCount(boolean returnCount) {
		this.returnCount = returnCount;
	}
	public boolean isFetchOutOfStockProducts() {
		return fetchOutOfStockProducts;
	}
	public void setFetchOutOfStockProducts(boolean fetchOutOfStockProducts) {
		this.fetchOutOfStockProducts = fetchOutOfStockProducts;
	}

	public boolean isFetchProductsWithDiscountOnly() {
		return fetchProductsWithDiscountOnly;
	}
	public void setFetchProductsWithDiscountOnly(boolean fetchProductsWithDiscountOnly) {
		this.fetchProductsWithDiscountOnly = fetchProductsWithDiscountOnly;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MainRequestObject [serialNumber=");
		builder.append(serialNumber);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", dbCode=");
		builder.append(dbCode);
		builder.append(", serviceId=");
		builder.append(serviceId);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", userInfo=");
		builder.append(userInfo);
		builder.append(", orderInfo=");
		builder.append(orderInfo);
		builder.append(", productInfo=");
		builder.append(productInfo);
		builder.append(", queryInfo=");
		builder.append(queryInfo);
		builder.append(", wislist=");
		builder.append(wishlist);
		builder.append(", invoiceInfo=");
		builder.append(invoiceInfo);
		builder.append(", ids=");
		builder.append(ids);
		builder.append(", returnCount=");
		builder.append(returnCount);
		builder.append(", fetchOutOfStockProducts=");
		builder.append(fetchOutOfStockProducts);
		builder.append(", fetchProductsWithDiscountOnly=");
		builder.append(fetchProductsWithDiscountOnly);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", userManagementServiceAction=");
		builder.append(userManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
