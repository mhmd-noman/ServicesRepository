package order.management.services.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Order;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import database.manager.methods.AbstractCommonDbMethods;
import order.management.services.beans.OrderManagementRequest;

public class OrderManagementServicesDaoImpl extends AbstractOrderManagementServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderManagementServicesDaoImpl.class);
	
	@Override
	public List<Order> getOrders(OrderManagementRequest productsManagementRequest, Connection connection) {
		List<Object> paramList = null;
		List<Map<Integer, Object>> productsResultSet = null;
		StringBuilder query = null;
		
		paramList = new ArrayList<>();
		query = new StringBuilder(AbstractOrderManagementServicesDao.GET_ORDERS);
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getOrderId())) {
			query.append(AbstractOrderManagementServicesDao.ORDER_IDs);
			paramList.add(productsManagementRequest.getOrder().getOrderId());
		}
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getCustName())) {
			query.append(AbstractOrderManagementServicesDao.CUSTOMER_NAME);
			paramList.add(productsManagementRequest.getOrder().getCustName());
		}
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getArea())) {
			query.append(AbstractOrderManagementServicesDao.ORDER_AREA);
			paramList.add(productsManagementRequest.getOrder().getArea());
		}
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getCity())) {
			query.append(AbstractOrderManagementServicesDao.ORDER_CITY);
			paramList.add(productsManagementRequest.getOrder().getCity());
		}
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getState())) {
			query.append(AbstractOrderManagementServicesDao.ORDER_STATE);
			paramList.add(productsManagementRequest.getOrder().getState());
		}
		if (!Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getCountry())) {
			query.append(AbstractOrderManagementServicesDao.ORDER_COUNTRY);
			paramList.add(productsManagementRequest.getOrder().getCountry());
		}
		if (!Utils.isNullOrEmptyCollection(paramList)) {
			logger.info(logger.isInfoEnabled() ? "Going to fetch products by using query: " +query+ " with paramters: "+ paramList: null);
			productsResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
		}
		return prepareProductsData(productsResultSet);
	}
	
	private static List<Order> prepareProductsData(List<Map<Integer, Object>> productsResultSet) {
		List<Order> orders = new ArrayList<>();
		Order order = null;
		int index = 0;
		if (null != productsResultSet) {
			for (Map<Integer, Object> productRow : productsResultSet) {
				order = new Order();
				order.setOrderId(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setOrderDescription(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustName(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustPhone(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustPhone2(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustAddress1(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustAddress2(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCustEmail(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setArea(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setCity(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setState(null != productRow.get(++index) ? (String)productRow.get(index): null);
				order.setOrderOrgAmount(null != productRow.get(++index) ? (Double)productRow.get(index): null);
				order.setOrderRtlAmount(null != productRow.get(++index) ? (Double)productRow.get(index): null);
				order.setOrderCalcDiscount(null != productRow.get(++index) ? (Double)productRow.get(index): null);
				order.setCreatedOn(null != productRow.get(++index) ? (Date)productRow.get(index): null);
				order.setCancelledAt(null != productRow.get(++index) ? (Date)productRow.get(index): null);
				order.setOrderStatus(null != productRow.get(++index) ? (String)productRow.get(index): null);
				orders.add(order);
				index = 0;
			}
		}
		return orders;
	}
	
	@Override
	public void placeOrder(OrderManagementRequest orderManagementRequest, Connection connection) {
		List<Object> paramList = null;
		if (null != orderManagementRequest && null != orderManagementRequest.getOrder()) {
			paramList = new ArrayList<>();
			paramList.add(orderManagementRequest.getOrder().getOrderId());
			paramList.add(orderManagementRequest.getOrder().getOrderDescription());
			paramList.add(orderManagementRequest.getOrder().getCustName());
			paramList.add(orderManagementRequest.getOrder().getCustPhone());
			paramList.add(orderManagementRequest.getOrder().getCustPhone2());
			paramList.add(orderManagementRequest.getOrder().getCustAddress1());
			paramList.add(orderManagementRequest.getOrder().getCustAddress2());
			paramList.add(orderManagementRequest.getOrder().getCustEmail());
			paramList.add(orderManagementRequest.getOrder().getArea());
			paramList.add(orderManagementRequest.getOrder().getCity());
			paramList.add(orderManagementRequest.getOrder().getState());
			paramList.add(orderManagementRequest.getOrder().getCountry());
			paramList.add(orderManagementRequest.getOrder().getOrderOrgAmount());
			paramList.add(orderManagementRequest.getOrder().getOrderRtlAmount());
			paramList.add(orderManagementRequest.getOrder().getOrderCalcDiscount());
			paramList.add(orderManagementRequest.getOrder().getOrderStatus());
			logger.info(logger.isInfoEnabled() ? "Going to insert order by using query: " +AbstractOrderManagementServicesDao.PLACE_ORDER+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractOrderManagementServicesDao.PLACE_ORDER, paramList, connection);
		}
	}
	
	@Override
	public void updateOrder(OrderManagementRequest orderManagementRequest, Connection connection) {
		List<Object> paramList = null;
		StringBuilder query = null;
		StringBuilder updateColumns = new StringBuilder();
		if (null != orderManagementRequest.getOrder()) {
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getOrderDescription())) {
				updateColumns.append("order_description = '" +orderManagementRequest.getOrder().getOrderDescription()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustName())) {
				updateColumns.append("cust_name = " +orderManagementRequest.getOrder().getCustName()+ ",");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustPhone())) {
				updateColumns.append("cust_phone = '" +orderManagementRequest.getOrder().getCustPhone()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustPhone2())) {
				updateColumns.append("cust_phone2 = '" +orderManagementRequest.getOrder().getCustPhone2()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustAddress1())) {
				updateColumns.append("cust_address = '" +orderManagementRequest.getOrder().getCustAddress1()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustAddress2())) {
				updateColumns.append("cust_address2 = '" +orderManagementRequest.getOrder().getCustAddress2()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustEmail())) {
				updateColumns.append("cust_email = '" +orderManagementRequest.getOrder().getCustEmail()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getArea())) {
				updateColumns.append("area = '" +orderManagementRequest.getOrder().getArea()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCity())) {
				updateColumns.append("city = '" +orderManagementRequest.getOrder().getCity()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getState())) {
				updateColumns.append("state = '" +orderManagementRequest.getOrder().getState()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCountry())) {
				updateColumns.append("country = '" +orderManagementRequest.getOrder().getMfgDate()+ "',");
			}
			if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderOrgAmount())) {
				updateColumns.append("order_org_amount = '" +orderManagementRequest.getOrder().getOrderOrgAmount()+ "',");
			}
			if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderRtlAmount())) {
				updateColumns.append("order_rtl_amount = '" +orderManagementRequest.getOrder().getOrderRtlAmount()+ "',");
			}
			if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderCalcDiscount())) {
				updateColumns.append("order_calc_discount = '" +orderManagementRequest.getOrder().getOrderCalcDiscount()+ "',");
			}
			if (CommonConstants.OPTION_F.equalsIgnoreCase(orderManagementRequest.getOrder().getOrderStatus())) {
				updateColumns.append("cancelled_at = '" +new Timestamp(System.currentTimeMillis())+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getOrderStatus())) {
				updateColumns.append("order_status = '" + orderManagementRequest.getOrder().getOrderStatus()+ "',");
			}
		}
		if (!Utils.validateIfNullOrEmptyString(updateColumns.toString().toString())) {
			query = new StringBuilder("update orders set " +updateColumns.toString().substring(0, updateColumns.length() - 1)+ " where order_id = '" +orderManagementRequest.getOrder().getOrderId()+ "'");
			logger.debug(logger.isDebugEnabled() ? "Going to update order by using query: " +query.toString()+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(query.toString(), paramList, connection);	
		}
	}

	public void removeOrder(OrderManagementRequest productsManagementRequest, Connection connection) {
		List<Object> paramList = null;
		if (null != productsManagementRequest.getOrder()
				&& !Utils.validateIfNullOrEmptyString(productsManagementRequest.getOrder().getOrderId())) {
			paramList = new ArrayList<>();
			paramList.add(productsManagementRequest.getOrder().getOrderId());
			logger.info(logger.isInfoEnabled() ? "Going to remove order by using query: " +AbstractOrderManagementServicesDao.REMOVE_ORDER+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractOrderManagementServicesDao.REMOVE_ORDER, paramList, connection);
		}
	}
}
