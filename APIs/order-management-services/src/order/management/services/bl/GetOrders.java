package order.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Order;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;

public class GetOrders extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetOrders.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest,  Connection connection) {
		OrderManagementResponse ordersManagementResponse = null;
		List<Order> ordersList = null;

		ordersManagementResponse = new OrderManagementResponse();
		ordersList = new ArrayList<>();
		
		if (null == ordersManagementRequest.getOrder()) {
			ordersManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			ordersManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
			return ordersManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? "Going to create product for username: ": null);
		ordersList = AbstractOrderManagementServicesDao.getInstance().getOrders(ordersManagementRequest, connection);
		if (!Utils.isNullOrEmptyCollection(ordersList)) {
			logger.info(logger.isInfoEnabled() ? "Retrieved Orders: "+ ordersList: null);
			ordersManagementResponse.setOrders(ordersList);
		}
		ordersManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		ordersManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return ordersManagementResponse;
	}
}
