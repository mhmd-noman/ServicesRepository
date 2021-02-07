package customer.services.beans;

import java.util.List;

import common.beans.Query;
import common.beans.Wishlist;

public class CustomerServicesResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private List<Query> queries = null;
	private List<Wishlist> wishlist = null;

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
	public List<Query> getQueries() {
		return queries;
	}
	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}
	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerServicesResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", queries=");
		builder.append(queries);
		builder.append(", wishlist=");
		builder.append(wishlist);
		builder.append("]");
		return builder.toString();
	}
}