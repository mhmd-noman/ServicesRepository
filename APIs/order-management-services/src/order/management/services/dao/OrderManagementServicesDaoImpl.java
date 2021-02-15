package order.management.services.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Order;
import common.beans.Product;
import common.exception.handling.BaseException;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import database.manager.methods.AbstractCommonDbMethods;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.utils.Constants;

public class OrderManagementServicesDaoImpl extends AbstractOrderManagementServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderManagementServicesDaoImpl.class);
	
	@Override
	public Map<Integer, Order> getOrders(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> productsResultSet = null;
		StringBuilder query = null;
		try {
			paramList = new ArrayList<>();
			query = new StringBuilder(AbstractOrderManagementServicesDao.GET_ORDERS);
			
			if (!Utils.isNullOrEmptyCollection(orderManagementRequest.getOrder().getOrderIds())) {
				query = new StringBuilder(AbstractOrderManagementServicesDao.GET_ORDERS);
				query.append(AbstractOrderManagementServicesDao.ORDER_IDs);
				query = new StringBuilder(query.toString().replace("@orders_ids", Utils.prepareInClauseString(orderManagementRequest.getOrder().getOrderIds())));
			} else {
				query = new StringBuilder(AbstractOrderManagementServicesDao.GET_ORDERS);
			}
			
			if (null != orderManagementRequest.getOrder()) {
				if (!Utils.validateIfNullOrInvalidInteger(orderManagementRequest.getOrder().getOrderId())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_ID);
					//query.replace("@orders_ids", );
					paramList.add(orderManagementRequest.getOrder().getOrderId());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustName())) {
					query.append(AbstractOrderManagementServicesDao.CUSTOMER_NAME);
					query = new StringBuilder(query.toString().replace("@custName", orderManagementRequest.getOrder().getCustName()));
					paramList.add(orderManagementRequest.getOrder().getCustName());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustPhone())) {
					query.append(AbstractOrderManagementServicesDao.CUSTOMER_PHONE_1);
					paramList.add(orderManagementRequest.getOrder().getCustPhone());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCustPhone2())) {
					query.append(AbstractOrderManagementServicesDao.CUSTOMER_PHONE_2);
					paramList.add(orderManagementRequest.getOrder().getCustPhone2());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getArea())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_AREA);
					paramList.add(orderManagementRequest.getOrder().getArea());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCity())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_CITY);
					paramList.add(orderManagementRequest.getOrder().getCity());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getState())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_STATE);
					paramList.add(orderManagementRequest.getOrder().getState());
				}
				if (!Utils.validateIfNullOrEmptyString(orderManagementRequest.getOrder().getCountry())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_COUNTRY);
					paramList.add(orderManagementRequest.getOrder().getCountry());
				}

				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderOrgAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_ORG_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderOrgAmount());
				}
				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromOrgAmount())
						&& Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToOrgAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_FROM_ORG_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderFromOrgAmount());
				} else if (Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromOrgAmount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToOrgAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_TO_ORG_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderToOrgAmount());
				} else if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromOrgAmount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToOrgAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_ORG_AMOUNT_RANGE);
					paramList.add(orderManagementRequest.getOrder().getOrderFromOrgAmount());
					paramList.add(orderManagementRequest.getOrder().getOrderToOrgAmount());
				}

				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderRtlAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_RTL_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderRtlAmount());
				}
				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromRtlAmount())
						&& Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToRtlAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_FROM_RTL_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderFromRtlAmount());
				} else if (Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromRtlAmount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToRtlAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_TO_RTL_AMOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderToRtlAmount());
				} else if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromRtlAmount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToRtlAmount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_RTL_AMOUNT_RANGE);
					paramList.add(orderManagementRequest.getOrder().getOrderFromRtlAmount());
					paramList.add(orderManagementRequest.getOrder().getOrderToRtlAmount());
				}

				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderCalcDiscount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_DISCOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderCalcDiscount());
				}
				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromCalcDiscount())
						&& Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToCalcDiscount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_FROM_DISCOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderFromCalcDiscount());
				} else if (Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromCalcDiscount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToCalcDiscount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_TO_DISCOUNT);
					paramList.add(orderManagementRequest.getOrder().getOrderToCalcDiscount());
				} else if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderFromCalcDiscount())
						&& !Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderToCalcDiscount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_DISCOUNT_RANGE);
					paramList.add(orderManagementRequest.getOrder().getOrderFromCalcDiscount());
					paramList.add(orderManagementRequest.getOrder().getOrderToCalcDiscount());
				}
				
				if (!Utils.validateIfNullOrInvalidDouble(orderManagementRequest.getOrder().getOrderCalcDiscount())) {
					query.append(AbstractOrderManagementServicesDao.ORDER_DATE);
					paramList.add(orderManagementRequest.getOrder().getCreatedOn());
				}
				if (Utils.isValidDate(orderManagementRequest.getOrder().getFromDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)
						&& !Utils.isValidDate(orderManagementRequest.getOrder().getToDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)) {
					query.append(AbstractOrderManagementServicesDao.ORDER_FROM_DATE);
					paramList.add(orderManagementRequest.getOrder().getFromDate());
				} else if (!Utils.isValidDate(orderManagementRequest.getOrder().getFromDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)
						&& Utils.isValidDate(orderManagementRequest.getOrder().getToDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)) {
					query.append(AbstractOrderManagementServicesDao.ORDER_TO_DATE);
					paramList.add(orderManagementRequest.getOrder().getToDate());
				} else if (Utils.isValidDate(orderManagementRequest.getOrder().getFromDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)
						&& Utils.isValidDate(orderManagementRequest.getOrder().getToDate(), CommonConstants.DATE_FORMAT_HIPHENS_YYYY_M_MDD)) {
					query.append(AbstractOrderManagementServicesDao.ORDERS_INTERVAL);
					paramList.add(orderManagementRequest.getOrder().getFromDate());
					paramList.add(orderManagementRequest.getOrder().getToDate());
				}
			}
			
			if (orderManagementRequest.isFetchCompletedOrdersOnly()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch completed orders as isFetchCompletedOrdersOnly:[" +orderManagementRequest.isFetchCompletedOrdersOnly()+ "]": null);
				query.append(AbstractOrderManagementServicesDao.FETCH_COMPLETED_ORDERS);
			}
			if (orderManagementRequest.isFetchCencelledOrdersOnly()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch cencelled orders as isFetchCencelledOrdersOnly:[" +orderManagementRequest.isFetchCencelledOrdersOnly()+ "]": null);
				query.append(AbstractOrderManagementServicesDao.FETCH_CANCELLED_ORDERS);
			}
			if (orderManagementRequest.isFetchInProgressOrdersOnly()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch inprogress orders as isFetchInProgressOrdersOnly:[" +orderManagementRequest.isFetchInProgressOrdersOnly()+ "]": null);
				query.append(AbstractOrderManagementServicesDao.FETCH_INPROGRESS_ORDERS);
			}
			
			if (!Utils.validateIfNullOrInvalidInteger(orderManagementRequest.getPageNo()) && !Utils.validateIfNullOrInvalidInteger(orderManagementRequest.getPageSize())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch orders as pageNo:[" +orderManagementRequest.getPageNo()+ "] and pageSize:[" +orderManagementRequest.getPageSize()+"]": null);
				query.append(AbstractOrderManagementServicesDao.ORDER_PAGINATION_SUPPORT);
				paramList.add(((orderManagementRequest.getPageNo() - 1) * orderManagementRequest.getPageSize()));
				paramList.add(orderManagementRequest.getPageSize());
			}

			query.append(AbstractOrderManagementServicesDao.ORDER_BY_CREATED_ON);
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch orders by using query: " +query+ " with paramters: "+ paramList: null);
			productsResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
		} catch (Exception ex) {
			logger.warn("##Exception## while getting orders ...");
			throw new BaseException(ex);
		}
		return prepareOrdersData(productsResultSet);
	}
	
	private static Map<Integer, Order> prepareOrdersData(List<Map<Integer, Object>> productsResultSet) throws BaseException {
		Map<Integer, Order> orders = new ConcurrentHashMap<>();
		Order order = null;
		Product product = null;
		int index = 0;
		try {
			if (null != productsResultSet) {
				for (Map<Integer, Object> productRow : productsResultSet) {
					++index;
					product = new Product();
					if (null != orders.get((Integer)productRow.get(index))) {
						order = orders.get((Integer)productRow.get(index));
					} else {
						order = new Order();
					}   
					order.setOrderId(null != productRow.get(index) ? (Integer)productRow.get(index): null);
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
					order.setCountry(null != productRow.get(++index) ? (String)productRow.get(index): null);
					order.setOrderOrgAmount(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					order.setOrderRtlAmount(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					order.setOrderCalcDiscount(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					order.setCreatedOn(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					order.setCancelledAt(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					order.setOrderStatus(null != productRow.get(++index) ? (String)productRow.get(index): null);
	
					product.setOrderedQuantity(null != productRow.get(++index) ? (Integer)productRow.get(index): null);
					product.setOrgPrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setRtlPrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setDiscountWhenOrdered(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setNetPrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setId(null != productRow.get(++index) ? (Integer)productRow.get(index): null);
					product.setName(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCompany(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCategory(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setFlavour(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setQuantity(null != productRow.get(++index) ? (Integer)productRow.get(index): null);
					product.setWeight(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setServings(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setServingSize(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setOrgPrice(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setDiscount(null != productRow.get(++index) ? ((BigDecimal)productRow.get(index)).doubleValue(): null);
					product.setMfgDate(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					product.setExpiryDate(null != productRow.get(++index) ? (Date)productRow.get(index): null);
					product.setBarCode(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setDirectiontoUse(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setDescription(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setCreatedOn(null != productRow.get(++index) ? ((Date)productRow.get(index)): null);
					product.setLastUpdatedOn(null != productRow.get(++index) ? ((Date)productRow.get(index)): null);
					product.setImagePath(null != productRow.get(++index) ? (String)productRow.get(index): null);
					product.setIsActive(null != productRow.get(++index) ? (CommonConstants.OPTION_Y.equalsIgnoreCase((String)productRow.get(index)) ? CommonConstants.OPTION_Y : CommonConstants.OPTION_N): null);
					if (null != order.getOrderedProducts()) {
						order.getOrderedProducts().add(product);
					} else {
						order.setOrderedProducts(new ArrayList<>());
						order.getOrderedProducts().add(product);
					}
					orders.put(order.getOrderId(), order);
					index = 0;
				}
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while preparing orders data ...");
			throw new BaseException(ex);
		}
		return orders;
	}
	
	@Override
	public void placeOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		Integer orderId = null;
		try {
			if (null != orderManagementRequest && null != orderManagementRequest.getOrder()) {
				paramList = new ArrayList<>();
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
				paramList.add(new Date(System.currentTimeMillis()));
				paramList.add(orderManagementRequest.getOrder().getCancelledAt());
				paramList.add(Constants.IN_PROGRESS_ORDER_STATUS); // order status
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert order by using query: " +AbstractOrderManagementServicesDao.PLACE_ORDER+ " with paramters: "+ paramList: null);
				orderId = AbstractCommonDbMethods.getInstance().updateWithKeyReturn(AbstractOrderManagementServicesDao.PLACE_ORDER, paramList, connection);
			}
			orderManagementRequest.getOrder().setOrderId(orderId);
			addOrderProducts(orderManagementRequest.getOrder(), connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while placing order ...");
			throw new BaseException(ex);
		}
	}
	
	private void addOrderProducts(Order order, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Object> paramListForPrductsQntity = null;
		List<String> queries = new ArrayList<>();
		List<String> queriesForPrductsQntity = new ArrayList<>();
		try {
			paramList = new ArrayList<>();
			paramListForPrductsQntity = new ArrayList<>();
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to add "+order.getOrderedProducts().size()+" products for order: [" +order.getOrderId()+ "]": null);
			for (Product product : order.getOrderedProducts()) {
				queries.add(AbstractOrderManagementServicesDao.INSERT_ORDER_PRODUCTS);
				paramList.add(order.getOrderId());
				paramList.add(product.getId());
				paramList.add(product.getName());
				paramList.add(product.getOrderedQuantity());
				paramList.add(product.getOrgPrice());
				paramList.add(Utils.getRetailPrice(product.getOrgPrice(), product.getDiscount()));
				paramList.add(product.getDiscount());
				paramList.add(Utils.getRetailPrice(Utils.getTotalPrice(product.getOrgPrice(),  product.getOrderedQuantity()), product.getDiscount())); // getting retail price having total price of products
//				// Maintaining Quantity
				queriesForPrductsQntity.add(AbstractOrderManagementServicesDao.SUBT_PRODUCTS_QUANTITY);
				paramListForPrductsQntity.add(product.getOrderedQuantity());
				paramListForPrductsQntity.add(product.getId());
			}
			AbstractCommonDbMethods.getInstance().executeBatch(queries, paramList, connection);
			AbstractCommonDbMethods.getInstance().executeBatch(queriesForPrductsQntity, paramListForPrductsQntity, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding orderec products ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public void updateOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		StringBuilder query = null;
		StringBuilder updateColumns = new StringBuilder();
		try {
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
					updateColumns.append("country = '" +orderManagementRequest.getOrder().getCountry()+ "',");
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
		} catch (Exception ex) {
			logger.warn("##Exception## while updating order ...");
			throw new BaseException(ex);
		}
	}

	public void cancelOrder(OrderManagementRequest orderManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		Map<Integer, Order> orders = new ConcurrentHashMap<>();
		try {
			orders = getOrders(orderManagementRequest, connection);
			if (null != orders) {
				if (null != orderManagementRequest.getOrder()
						&& !Utils.validateIfNullOrInvalidInteger(orderManagementRequest.getOrder().getOrderId())) {
					paramList = new ArrayList<>();
					paramList.add(new Date(System.currentTimeMillis()));
					paramList.add(orderManagementRequest.getOrder().getOrderId());
					logger.info(logger.isInfoEnabled() ? "Going to cancel order by using query: " +AbstractOrderManagementServicesDao.CANCEL_ORDER+ " with paramters: "+ paramList: null);
					AbstractCommonDbMethods.getInstance().update(AbstractOrderManagementServicesDao.CANCEL_ORDER, paramList, connection);
				}
				logger.info(logger.isInfoEnabled() ? "Going to retain products quantity for order: [" +orders.get(orderManagementRequest.getOrder().getOrderId()).getOrderId()+"]": null);
				retainProductsQuantity(orders.get(orderManagementRequest.getOrder().getOrderId()), connection);
			}
			
		} catch (Exception ex) {
			logger.warn("##Exception## while removing product ...");
			throw new BaseException(ex);
		}
	}
	
	private void retainProductsQuantity(Order order, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<String> queries = new ArrayList<>();
		try {
			paramList = new ArrayList<>();
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to retain total "+order.getOrderedProducts().size()+" products for order: [" +order.getOrderId()+ "]": null);
			for (Product product : order.getOrderedProducts()) {
				queries.add(AbstractOrderManagementServicesDao.ADD_PRODUCTS_QUANTITY);
				paramList.add(product.getOrderedQuantity());
				paramList.add(product.getId());
			}
			AbstractCommonDbMethods.getInstance().executeBatch(queries, paramList, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding orderec products ...");
			throw new BaseException(ex);
		}
	}
}
