package product.management.services.beans;

import java.util.List;

import common.beans.Product;
import common.enums.ProductManagementServiceAction;

public class ProductManagementRequest {
	private Product product = null;
	private List<Integer> productIds = null;
	private boolean fetchOutOfStockProducts = false;
	private ProductManagementServiceAction productManagementServiceAction = null;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductManagementServiceAction getProductManagementServiceAction() {
		return productManagementServiceAction;
	}
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> ids) {
		this.productIds = ids;
	}
	public boolean isFetchOutOfStockProducts() {
		return fetchOutOfStockProducts;
	}
	public void setFetchOutOfStockProducts(boolean fetchOutOfStockProducts) {
		this.fetchOutOfStockProducts = fetchOutOfStockProducts;
	}
	public void setProductManagementServiceAction(ProductManagementServiceAction productManagementServiceAction) {
		this.productManagementServiceAction = productManagementServiceAction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductManagementRequest [product=");
		builder.append(product);
		builder.append(", productManagementServiceAction=");
		builder.append(productManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
