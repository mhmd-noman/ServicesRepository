package product.management.services.dao;

import java.sql.Connection;
import java.util.List;

import common.beans.Product;
import common.exception.handling.BaseException;
import product.management.services.beans.ProductManagementRequest;

public abstract class AbstractProductManagementServicesDao {
	public static final String ADD_PRODUCT       = "insert into products (name, company, category, flavour, quantity, weight, servings, serving_size, purchase_price, org_price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, image, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_PRODUCT    = "delete from products where id = ?";
	public static final String GET_PRODUCTS      = "select id, name, company, category, flavour, quantity, weight, servings, serving_size, purchase_price, org_price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, image, is_active from products where id is not null ";
	public static final String PRODUCT_IDS       = "and id in (@product_ids) ";
	public static final String PRODUCT_ID        = "and id = ? ";
	public static final String PRODUCT_NAME      = "and name = ? ";
	public static final String PRODUCT_FLAVOUR   = "and flavour = ? ";
	public static final String PRODUCT_CATEGORY  = "and category = ? ";
	public static final String PRODUCT_COMPANY   = "and company = ? ";
	public static final String PRODUCT_WEIGHT         = "and weight = ? ";
	public static final String PRODUCT_SERVING        = "and servings = ? ";
	
	public static final String PRODUCT_PRICE          = "and org_price = ? ";
	public static final String FROM_PRODUCT_PRICE     = "and org_price >= ? ";
	public static final String TO_PRODUCT_PRICE       = "and org_price <= ? ";
	public static final String PRODUCT_PRICE_RANGE    = "and org_price between ? and ? ";
	
	public static final String PRODUCTS_WITH_DISCOUNT        = "and discount = ? ";
	public static final String PRODUCTS_WITH_DISCOUNT_ONLY   = "and discount is not null ";
	public static final String PRODUCTS_WITH_DISCOUNT_FROM   = "and discount >= ? ";
	public static final String PRODUCTS_WITH_DISCOUNT_TO     = "and discount <= ? ";
	public static final String PRODUCTS_WITH_DISCOUNT_RANGE  = "and discount between ? and ? ";
	
	public static final String PRODUCT_NAME_RESPONSIVE       = "and name like '%@name%' ";
	public static final String PRODUCT_PAGINATION_SUPPORT    = "limit ? , ? ";
	//public static final String PRODUCT_PAGINATION_SUPPORT  = "limit (page no * page size) , (page_size) ";
	//public static final String PRODUCT_PAGINATION_SUPPORT  = "offset (page no * page size) rows fetch next (page_size) rows only ";
	public static final String IS_ACTIVE_PRODUCT = "and is_active = 'Y' ";

	public static AbstractProductManagementServicesDao getInstance() throws BaseException {
		return new ProductManagementServicesDaoImpl();
	}
	public abstract List<Product> getProducts(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException;
	public abstract void addProduct(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException;
	public abstract void updateProduct(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException;
	public abstract void deleteProduct(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException;
}
