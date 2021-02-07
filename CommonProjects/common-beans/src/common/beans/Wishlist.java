package common.beans;

import java.util.List;

public class Wishlist {
	private String username = null;
	private String wishlist = null;
	private List<Product> products = null;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWishlist() {
		return wishlist;
	}
	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Wishlist [username=");
		builder.append(username);
		builder.append(", wishlist=");
		builder.append(wishlist);
		builder.append(", products=");
		builder.append(products);
		builder.append("]");
		return builder.toString();
	}
}
