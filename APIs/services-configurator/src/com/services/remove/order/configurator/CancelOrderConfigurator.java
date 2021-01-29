package com.services.remove.order.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.enums.OrderManagementServiceAction;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import order.management.services.OrderManagementService;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;

public class CancelOrderConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(CancelOrderConfigurator.class);

	public MainResponseObject cancelOrder(MainRequestObject mainRequestObject, Connection con) throws BaseException {
		MainResponseObject mainResponseObject = new MainResponseObject();
		OrderManagementService orderManagementService = new OrderManagementService();
		OrderManagementRequest orderManagementRequest = new OrderManagementRequest();
		OrderManagementResponse orderManagementResponse = new OrderManagementResponse();
		mapRequest(mainRequestObject, orderManagementRequest);
		orderManagementResponse = orderManagementService.orderManagementService(orderManagementRequest, con);
		mapResponse(mainResponseObject, orderManagementResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, OrderManagementRequest orderManagementRequest) {
		if (null != mainRequestObject && null != mainRequestObject.getOrderInfo()) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for cancelOrder: [" +mainRequestObject+ "]": null);
			orderManagementRequest.setOrder(mainRequestObject.getOrderInfo());
			mainRequestObject.setServiceId(OrderManagementServiceAction.CANCEL_ORDER.toString());
		}
		orderManagementRequest.setOrderManagementServiceAction(OrderManagementServiceAction.CANCEL_ORDER);
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, OrderManagementResponse orderManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for cancelOrder: [" +orderManagementResponse+ "]": null);
		if (null != orderManagementResponse) {
			mainResponseObject.setResponseCode(orderManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(orderManagementResponse.getResponseDesc());
			mainResponseObject.setOrders(orderManagementResponse.getOrders());
		}
	}
}
