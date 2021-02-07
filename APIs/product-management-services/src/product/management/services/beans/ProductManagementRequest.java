package product.management.services.beans;

import java.util.List;

import common.beans.Product;
import common.enums.ProductManagementServiceAction;

public class ProductManagementRequest {
	private Product product = null;
	private List<Integer> productIds = null;
	private Integer pageNo   = null;
	private Integer pageSize = null;
	private boolean returnCount = false;
	private boolean fetchOutOfStockProducts = false;
	private boolean fetchProductsWithDiscountOnly = false;
	private boolean ifCallingFromAddProductService = false;
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
	public boolean isIfCallingFromAddProduct() {
		return ifCallingFromAddProductService;
	}
	public void setIfCallingFromAddProduct(boolean ifCallingFromAddProductService) {
		this.ifCallingFromAddProductService = ifCallingFromAddProductService;
	}
	public void setProductManagementServiceAction(ProductManagementServiceAction productManagementServiceAction) {
		this.productManagementServiceAction = productManagementServiceAction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductManagementRequest [product=");
		builder.append(product);
		builder.append(", productIds=");
		builder.append(productIds);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", returnCount=");
		builder.append(returnCount);
		builder.append(", fetchOutOfStockProducts=");
		builder.append(fetchOutOfStockProducts);
		builder.append(", fetchProductsWithDiscountOnly=");
		builder.append(fetchProductsWithDiscountOnly);
		builder.append(", ifCallingFromAddProductService=");
		builder.append(ifCallingFromAddProductService);
		builder.append(", productManagementServiceAction=");
		builder.append(productManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
