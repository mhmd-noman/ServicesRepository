package product.management.services.dao;

import java.sql.Connection;
import java.util.List;

import common.beans.Product;
import product.management.services.beans.ProductManagementRequest;

public abstract class AbstractProductManagementServicesDao {
	public static final String ADD_PRODUCT    = "insert into users (name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_PRODUCT = "update products set email = ?, enabled = ?, first_name = ?, last_name = ?, password = ?, phone = ? where username = ?";
	public static final String DELETE_PRODUCT = "delete products where id = ?";
	public static final String GET_PRODUCTS   = "select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where is_active = 'Y' ";
	public static final String PRODUCT_ID     = "and id = ? ";
	public static final String PRODUCT_NAME   = "and name = ? ";
	public static final String PRODUCT_COMPANY= "and name = ? ";
	public static final String PRODUCT_PRICE  = "and price = ? ";
	public static final String PRODUCT_WEIGHT = "and weight = ? ";

	public static AbstractProductManagementServicesDao getInstance() {
		return new ProductManagementServicesDaoImpl();
	}
	public abstract List<Product> getProducts(ProductManagementRequest productManagementRequest, Connection connection);
	public abstract void addProduct(ProductManagementRequest productManagementRequest, Connection connection);
	public abstract void updateProduct(ProductManagementRequest productManagementRequest, Connection connection);
	public abstract void deleteProduct(ProductManagementRequest productManagementRequest, Connection connection);
}
