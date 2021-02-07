package common.response;

import java.util.List;

import common.beans.Order;
import common.beans.Product;
import common.beans.Query;
import common.beans.User;
import common.beans.Wishlist;

public class MainResponseObject {
	private String responseCode = null;
	private String responseDesc = null;
	private String token = null; 
	private Integer transCount = null; 
	private List<User> users = null;
	private List<Product> products = null;
	private List<Order> orders = null;
	private List<Query> queries = null;
	private List<Wishlist> wishlists = null;

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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getTransCount() {
		return transCount;
	}
	public void setTransCount(Integer transCount) {
		this.transCount = transCount;
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
	public List<Wishlist> getWishlists() {
		return wishlists;
	}
	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MainResponseObject [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", token=");
		builder.append(token);
		builder.append(", transCount=");
		builder.append(transCount);
		builder.append(", users=");
		builder.append(users);
		builder.append(", products=");
		builder.append(products);
		builder.append(", orders=");
		builder.append(orders);
		builder.append(", queries=");
		builder.append(queries);
		builder.append(", wishlists=");
		builder.append(wishlists);
		builder.append("]");
		return builder.toString();
	}
}
