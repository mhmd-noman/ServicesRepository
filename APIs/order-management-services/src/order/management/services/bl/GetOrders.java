package order.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Order;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;
import order.management.services.dao.AbstractOrderManagementServicesDao;
import order.management.services.utils.Constants;

public class GetOrders extends AbstractOrderManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetOrders.class);

	public OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest,  Connection connection) throws BaseException {
		OrderManagementResponse ordersManagementResponse = null;
		Map<Integer, Order> ordersMap = null;
		List<Order> ordersList = null;
		try {
			ordersManagementResponse = new OrderManagementResponse();
			ordersList = new ArrayList<>();
			
			if (null == ordersManagementRequest.getOrder()) {
				ordersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				ordersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return ordersManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to get orders ... ": null);
			ordersMap = AbstractOrderManagementServicesDao.getInstance().getOrders(ordersManagementRequest, connection);
			
			if (!Utils.isNullOrEmptyCollection(ordersMap.values())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Retrieved Orders: "+ ordersList: null);
				ordersManagementResponse.setOrders(new ArrayList<Order>(ordersMap.values()));
			}
			ordersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			ordersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting orders ..."+ex);
			throw new BaseException(ex);
		}
		return ordersManagementResponse;
	}
}
