package order.management.services.dao;

import java.sql.Connection;
import java.util.List;

import common.beans.Order;
import order.management.services.beans.OrderManagementRequest;

public abstract class AbstractOrderManagementServicesDao {
	public static final String PLACE_ORDER           = "insert into orders (order_id, order_description, cust_name, cust_phone, cust_phone2, cust_address, cust_address2, cust_email, area, city, state, country, order_org_amount, order_rtl_amount, order_calc_discount, created_on, cancelled_at, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_ORDER_PRODUCTS = "insert into order_products (order_id, product_id, product_name, product_quantity, product_org_price, product_rtl_price, product_discount, product_net_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_ORDER          = "update products set email = ?, enabled = ?, first_name = ?, last_name = ?, password = ?, phone = ? where username = ?";
	public static final String REMOVE_ORDER          = "delete orders where order_id = ?";
	public static final String GET_ORDERS            = "select order_id, order_description, cust_name, cust_phone, cust_phone2, cust_address, cust_address2, cust_email, area, city, state, country, order_org_amount, order_rtl_amount, order_calc_discount, created_on, cancelled_at, order_status from orders where is_active = 'Y' ";
	public static final String ORDER_IDs             = "and order_id in (@orders_ids) ";
	public static final String CUSTOMER_NAME         = "and cust_name = ? ";
	public static final String ORDER_AREA            = "and area = ? ";
	public static final String ORDER_CITY            = "and city = ? ";
	public static final String ORDER_STATE           = "and state = ? ";
	public static final String ORDER_COUNTRY         = "and country = ? ";

	public static AbstractOrderManagementServicesDao getInstance() {
		return new OrderManagementServicesDaoImpl();
	}
	public abstract List<Order> getOrders(OrderManagementRequest orderManagementRequest, Connection connection);
	public abstract void placeOrder(OrderManagementRequest orderManagementRequest, Connection connection);
	public abstract void updateOrder(OrderManagementRequest orderManagementRequest, Connection connection);
	public abstract void removeOrder(OrderManagementRequest orderManagementRequest, Connection connection);
}
