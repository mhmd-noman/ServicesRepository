/**
* @author  Muhammad Noman
* @version 1.0
* @since   2020-October-10 
*/
package order.management.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.bl.AbstractOrderManagementServicesHandler;

public class OrderManagementService {
	private static final Logger logger = LoggerFactory.getLogger(OrderManagementService.class);
	
	public OrderManagementResponse orderManagementService(OrderManagementRequest orderManagementRequest, Connection con) {
		OrderManagementResponse orderManagementResponse = new OrderManagementResponse();
		if (null == orderManagementRequest) {
			orderManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			orderManagementResponse.setResponseDesc("Product Request Content has been Passed Empty.");
			return orderManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? "Going to call productManagementService Service": null);
		orderManagementResponse = AbstractOrderManagementServicesHandler.getInstance(orderManagementRequest.getOrderManagementServiceAction()).orderManagementService(orderManagementRequest, con);
		return orderManagementResponse;
	}
}
