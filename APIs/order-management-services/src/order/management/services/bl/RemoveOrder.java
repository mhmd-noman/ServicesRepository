package order.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;

public class RemoveOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(RemoveOrder.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest, Connection connection) {
		OrderManagementResponse orderManagementResponse = null;
		
		orderManagementResponse = new OrderManagementResponse();
		if (null == ordersManagementRequest.getOrder()
				|| Utils.validateIfNullOrInvalidInteger(ordersManagementRequest.getOrder().getOrderId())) {
			orderManagementResponse = new OrderManagementResponse();
			orderManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			orderManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
		}
		logger.info(logger.isInfoEnabled() ? "Going to delete product for user id: ": null);
		AbstractOrderManagementServicesDao.getInstance().removeOrder(ordersManagementRequest, connection);
		orderManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		orderManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return orderManagementResponse;
	}
}
