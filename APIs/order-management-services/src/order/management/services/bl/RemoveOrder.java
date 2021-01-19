package order.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;
import order.management.services.utils.Constants;

public class RemoveOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(RemoveOrder.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest, Connection connection) {
		OrderManagementResponse orderManagementResponse = null;
		
		orderManagementResponse = new OrderManagementResponse();
		if (null == ordersManagementRequest.getOrder()
				|| Utils.validateIfNullOrInvalidInteger(ordersManagementRequest.getOrder().getOrderId())) {
			orderManagementResponse = new OrderManagementResponse();
			orderManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			orderManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
		}
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to delete product for user id: ": null);
		AbstractOrderManagementServicesDao.getInstance().removeOrder(ordersManagementRequest, connection);
		orderManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
		orderManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		return orderManagementResponse;
	}
}
