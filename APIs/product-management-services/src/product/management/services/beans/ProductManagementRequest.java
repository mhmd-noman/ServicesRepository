package product.management.services.beans;

import common.beans.Product;
import common.enums.ProductManagementServiceAction;

public class ProductManagementRequest {
	private Product product = null;
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
