package order.management.services.dao;

import java.sql.Connection;
import java.util.Map;

import common.beans.Order;
import common.exception.handling.BaseException;
import order.management.services.beans.OrderManagementRequest;

public abstract class AbstractOrderManagementServicesDao {
	public static final String PLACE_ORDER           = "insert into orders (order_description, cust_name, cust_phone, cust_phone2, cust_address, cust_address2, cust_email, area, city, state, country, order_org_amount, order_rtl_amount, order_calc_discount, created_on, cancelled_at, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_ORDER_PRODUCTS = "insert into order_products (order_id, product_id, product_name, product_quantity, product_org_price, product_rtl_price, product_discount, product_net_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_ORDER          = "update products set email = ?, enabled = ?, first_name = ?, last_name = ?, password = ?, phone = ? where username = ?";
	public static final String REMOVE_ORDER          = "delete orders where order_id = ?";
	public static final String GET_ORDERS            = "select o.order_id, o.order_description, o.cust_name, o.cust_phone, o.cust_phone2, o.cust_address, o.cust_address2, o.cust_email, o.area, o.city, o.state, o.country, o.order_org_amount, o.order_rtl_amount, o.order_calc_discount, o.created_on, o.cancelled_at, o.order_status, op.product_quantity, op.product_org_price, op.product_rtl_price, op.product_discount, op.product_net_price, p.id, p.name, p.company, p.category, p.flavour, p.quantity, p.weight, p.servings, p.serving_size, p.price, p.discount, p.mfg_date, p.expiry_date, p.bar_code, p.direction_to_use, p.description, p.created_on, p.last_updated_on, p.is_active from orders o, order_products op, products p where o.order_id = op.order_id and op.product_id = p.id and o.order_status = 'Y' ";
	public static final String GET_ORDERS_DETAIL     = "select o.order_id, o.order_description, o.cust_name, o.cust_phone, o.cust_phone2, o.cust_address, o.cust_address2, o.cust_email, o.area, o.city, o.state, o.country, o.order_org_amount, o.order_rtl_amount, o.order_calc_discount, o.created_on, o.cancelled_at, o.order_status, op.product_quantity, op.product_org_price, op.product_rtl_price, op.product_discount, op.product_net_price from orders o, order_products op, products p where o.order_id = op.order_id and op.product_id = p.id and o.order_id in (@order_ids)";
	public static final String ORDER_IDs             = "and order_id in (@orders_ids) ";
	public static final String ORDER_ID              = "and order_id = ? ";
	public static final String CUSTOMER_NAME         = "and o.cust_name = ? ";
	public static final String ORDER_AREA            = "and o.area = ? ";
	public static final String ORDER_CITY            = "and o.city = ? ";
	public static final String ORDER_STATE           = "and o.state = ? ";
	public static final String ORDER_COUNTRY         = "and o.country = ? ";

	public static AbstractOrderManagementServicesDao getInstance() throws BaseException {
		return new OrderManagementServicesDaoImpl();
	}
	public abstract Map<Integer, Order> getOrders(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException;
	public abstract void placeOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException;
	public abstract void updateOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException;
	public abstract void removeOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException;
}
