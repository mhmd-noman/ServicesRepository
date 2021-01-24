package order.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;
import order.management.services.utils.Constants;

public class PlaceOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(PlaceOrder.class);
	
	public OrderManagementResponse orderManagementService(OrderManagementRequest orderManagementRequest,  Connection connection) throws BaseException {
		OrderManagementResponse orderManagementResponse = null;
		try {
			orderManagementResponse = new OrderManagementResponse();
			if (null == orderManagementRequest.getOrder() || Utils.isNullOrEmptyCollection(orderManagementRequest.getOrder().getOrderedProducts())) {
				orderManagementResponse = new OrderManagementResponse();
				orderManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				orderManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to add order for username: ": null);
			AbstractOrderManagementServicesDao.getInstance().placeOrder(orderManagementRequest, connection);
			orderManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			orderManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
			return orderManagementResponse;
		} catch (Exception ex) {
			logger.warn("##Exception## while placing order ...");
			throw new BaseException(ex);
		}
	}
}
