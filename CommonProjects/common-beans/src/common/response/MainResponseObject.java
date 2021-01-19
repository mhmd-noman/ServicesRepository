package common.response;

import java.util.List;

import common.beans.Order;
import common.beans.Product;
import common.beans.Query;
import common.beans.User;

public class MainResponseObject {
	private String responseCode = null;
	private String responseDesc = null;
	private List<User> users = null;
	private List<Product> products = null;
	private List<Order> orders = null;
	private List<Query> queries = null;

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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Query> getQueries() {
		return queries;
	}
	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", users=");
		builder.append(users);
		builder.append(", orders=");
		builder.append(orders);
		builder.append(", queries=");
		builder.append(queries);
		builder.append("]");
		return builder.toString();
	}
}
