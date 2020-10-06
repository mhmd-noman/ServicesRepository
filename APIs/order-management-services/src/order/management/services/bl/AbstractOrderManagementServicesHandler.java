package order.management.services.bl;

import java.sql.Connection;

import common.enums.OrderManagementServiceAction;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;

public abstract class AbstractOrderManagementServicesHandler {
	public static AbstractOrderManagementServicesHandler getInstance() {
		return new GetOrders();
	}

	public static AbstractOrderManagementServicesHandler getInstance(OrderManagementServiceAction orderManagementServiceAction) {
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

	public abstract OrderManagementResponse orderManagementService(OrderManagementRequest ordersManagementRequest,  Connection connection);
}