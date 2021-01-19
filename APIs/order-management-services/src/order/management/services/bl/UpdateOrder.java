package order.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.ResponseCodes;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;
import order.management.services.utils.Constants;

public class UpdateOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UpdateOrder.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest orderManagementRequest,  Connection connection) {
		OrderManagementResponse orderManagementResponse = null;
		
		orderManagementResponse = new OrderManagementResponse();
		if (null == orderManagementRequest.getOrder()) {
			orderManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			orderManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
			return orderManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to update user for user id: ": null);
		AbstractOrderManagementServicesDao.getInstance().updateOrder(orderManagementRequest, connection);
		orderManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
		orderManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		return orderManagementResponse;
	}
}
