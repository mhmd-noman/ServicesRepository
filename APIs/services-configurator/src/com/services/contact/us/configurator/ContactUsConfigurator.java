package com.services.contact.us.configurator;
import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import customer.services.CustomerServices;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.utils.CustomerServicesAction;
public class ContactUsConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(ContactUsConfigurator.class);
	
	public MainResponseObject contactUs(MainRequestObject mainRequestObject, Connection con) {
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
		if (null != mainRequestObject && null != mainRequestObject.getQueryInfo()) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for contactUs: [" +mainRequestObject+ "]": null);
			customerServicesRequest.setQuery(mainRequestObject.getQueryInfo());
			customerServicesRequest.setCustomerServicesAction(CustomerServicesAction.CONTACT_US);
		}
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, CustomerServicesResponse customerServicesResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for contactUs: [" +customerServicesResponse+ "]": null);
		if (null != customerServicesResponse) {
			mainResponseObject.setResponseCode(customerServicesResponse.getResponseCode());
			mainResponseObject.setResponseDesc(customerServicesResponse.getResponseDesc());
			mainResponseObject.setQueries(customerServicesResponse.getQueries());
		}
	}
}
