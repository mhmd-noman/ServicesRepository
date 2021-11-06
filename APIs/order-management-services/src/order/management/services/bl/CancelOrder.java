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

public class CancelOrder extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(CancelOrder.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest, Connection connection) throws BaseException {
		OrderManagementResponse orderManagementResponse = null;
		try {
			orderManagementResponse = new OrderManagementResponse();
			if (null == ordersManagementRequest.getOrder()
					|| Utils.validateIfNullOrInvalidInteger(ordersManagementRequest.getOrder().getOrderId())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Cannot proceed cancelOrder call as order info in request has been passed empty/null.": null);
				orderManagementResponse = new OrderManagementResponse();
				orderManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				orderManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return orderManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to cancel order for order id: [" +ordersManagementRequest.getOrder().getOrderId()+ "]": null);
			AbstractOrderManagementServicesDao.getInstance().cancelOrder(ordersManagementRequest, connection);
			orderManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			orderManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while removing order ..."+ex);
			throw new BaseException(ex);
		}
		return orderManagementResponse;
	}
}
