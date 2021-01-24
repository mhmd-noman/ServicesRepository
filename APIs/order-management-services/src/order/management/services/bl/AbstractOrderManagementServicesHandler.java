package order.management.services.bl;

import java.sql.Connection;

import common.enums.OrderManagementServiceAction;
import common.exception.handling.BaseException;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;

public abstract class AbstractOrderManagementServicesHandler {
	public static AbstractOrderManagementServicesHandler getInstance() throws BaseException {
		return new GetOrders();
	}

	public static AbstractOrderManagementServicesHandler getInstance(OrderManagementServiceAction orderManagementServiceAction) throws BaseException {
		AbstractOrderManagementServicesHandler abstractManagementOrdersHandler = null;
		switch(orderManagementServiceAction) {
		case GET_ORDERS:
			abstractManagementOrdersHandler = new GetOrders();
			break;
		case PLACE_ORDER:
			abstractManagementOrdersHandler = new PlaceOrder();
			break;
		case UPDATE_ORDER:
			abstractManagementOrdersHandler = new UpdateOrder();
			break;
		case REMOVE_ORDER:
			abstractManagementOrdersHandler = new RemoveOrder();
			break;
		default:
			abstractManagementOrdersHandler = new GetOrders();
			break;
		}
		return abstractManagementOrdersHandler;
	}

	public abstract OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest,  Connection connection) throws BaseException;
}