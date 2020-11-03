package com.services.place.order.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.services.configurator.ServicesConfigurator;

import common.enums.OrderManagementServiceAction;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import order.management.services.OrderManagementService;
import order.management.services.beans.OrderManagementRequest;
import order.management.services.beans.OrderManagementResponse;

public class PlaceOrderConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(ServicesConfigurator.class);
	public MainResponseObject placeOrder(MainRequestObject mainRequestObject, Connection con) {
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
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for order placement: [" +mainRequestObject+ "]": null);
			orderManagementRequest.setOrder(mainRequestObject.getOrderInfo());
		}
		orderManagementRequest.setOrderManagementServiceAction(OrderManagementServiceAction.PLACE_ORDER);
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, OrderManagementResponse orderManagementResponse) {
		if (null != orderManagementResponse) {
			logger.info(logger.isInfoEnabled() ? "Response recieved for order placement: [" +orderManagementResponse+ "]": null);
			mainResponseObject.setResponseCode(orderManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(orderManagementResponse.getResponseDesc());
			mainResponseObject.setOrders(orderManagementResponse.getOrders());
		}
	}
}
