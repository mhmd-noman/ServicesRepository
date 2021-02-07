package product.management.services.beans;

import java.util.List;

import common.beans.Product;

public class ProductManagementResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private List<Product> products = null;
	private Integer productsCount = null;

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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getProductsCount() {
		return productsCount;
	}
	public void setProductsCount(Integer productsCount) {
		this.productsCount = productsCount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductsManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", products=");
		builder.append(products);
		builder.append(", productsCount=");
		builder.append(productsCount);
		builder.append("]");
		return builder.toString();
	}
}
