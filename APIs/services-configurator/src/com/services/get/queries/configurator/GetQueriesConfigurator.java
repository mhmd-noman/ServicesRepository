package com.services.get.queries.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.enums.CustomerServicesAction;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import customer.services.CustomerServices;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;

public class GetQueriesConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(GetQueriesConfigurator.class);

	public MainResponseObject getQueries(MainRequestObject mainRequestObject, Connection con) throws BaseException {
		MainResponseObject mainResponseObject = new MainResponseObject();
		CustomerServices customerServices = new CustomerServices();
		CustomerServicesRequest customerServicesRequest = new CustomerServicesRequest();
		CustomerServicesResponse customerServicesResponse = new CustomerServicesResponse();
		mapRequest(mainRequestObject, customerServicesRequest);
		customerServicesResponse = customerServices.customerServices(customerServicesRequest, con);
		mapResponse(mainResponseObject, customerServicesResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, CustomerServicesRequest customerServicesRequest) {
		if (null != mainRequestObject) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for getQueries: [" +mainRequestObject+ "]": null);
			customerServicesRequest.setQuery(mainRequestObject.getQueryInfo());
		}
		customerServicesRequest.setCustomerServicesAction(CustomerServicesAction.GET_QUERIES);
		mainRequestObject.setServiceId(CustomerServicesAction.GET_QUERIES.toString());
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, CustomerServicesResponse customerServicesResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for getQueries: [" +customerServicesResponse+ "]": null);
		if (null != customerServicesResponse) {
			mainResponseObject.setResponseCode(customerServicesResponse.getResponseCode());
			mainResponseObject.setResponseDesc(customerServicesResponse.getResponseDesc());
			mainResponseObject.setQueries(customerServicesResponse.getQueries());
		}
	}
}
