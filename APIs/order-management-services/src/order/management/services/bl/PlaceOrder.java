package order.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;

public class PlaceOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(PlaceOrder.class);
	
	public OrderManagementResponse orderManagementService(OrderManagementRequest orderManagementRequest,  Connection connection) {
		OrderManagementResponse productManagementResponse = null;
		
		productManagementResponse = new OrderManagementResponse();
		if (null == orderManagementRequest.getOrder() || Utils.isNullOrEmptyCollection(orderManagementRequest.getOrder().getOrderedProducts())) {
			productManagementResponse = new OrderManagementResponse();
			productManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			productManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
		}
		logger.info(logger.isInfoEnabled() ? "Going to add order for username: ": null);
		AbstractOrderManagementServicesDao.getInstance().placeOrder(orderManagementRequest, connection);
		productManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productManagementResponse;
	}
}
