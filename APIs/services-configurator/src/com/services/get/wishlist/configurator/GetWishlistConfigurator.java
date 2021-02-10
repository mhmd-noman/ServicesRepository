package com.services.get.wishlist.configurator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.services.get.product.configurator.GetProductsConfigurator;

import common.beans.Product;
import common.enums.CustomerServicesAction;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import common.utilities.methods.Utils;
import customer.services.CustomerServices;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;

public class GetWishlistConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(GetWishlistConfigurator.class);

	public MainResponseObject getWishlist(MainRequestObject mainRequestObject, Connection con) throws BaseException {
		MainResponseObject mainResponseObject = new MainResponseObject();
		CustomerServices customerServices = new CustomerServices();
		CustomerServicesRequest customerServicesRequest = new CustomerServicesRequest();
		CustomerServicesResponse customerServicesResponse = new CustomerServicesResponse();
		mapRequest(mainRequestObject, customerServicesRequest);
		customerServicesResponse = customerServices.customerServices(customerServicesRequest, con);
		mapResponse(mainResponseObject, customerServicesResponse, con);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, CustomerServicesRequest customerServicesRequest) {
		if (null != mainRequestObject) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for getWishlist: [" +mainRequestObject+ "]": null);
			customerServicesRequest.setWishlist(mainRequestObject.getWishlist());
		}
		customerServicesRequest.setCustomerServicesAction(CustomerServicesAction.GET_WISHLIST);
		mainRequestObject.setServiceId(CustomerServicesAction.GET_WISHLIST.toString());
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, CustomerServicesResponse customerServicesResponse, Connection con) throws BaseException {
		logger.info(logger.isInfoEnabled() ? "Response recieved for getWishlist: [" +customerServicesResponse+ "]": null);
		if (null != customerServicesResponse) {
			mainResponseObject.setResponseCode(customerServicesResponse.getResponseCode());
			mainResponseObject.setResponseDesc(customerServicesResponse.getResponseDesc());
			mainResponseObject.setWishlists(customerServicesResponse.getWishlist());
			if (null != mainResponseObject.getWishlists()) {
				mainResponseObject.getWishlists().get(0).setProducts(new ArrayList<>());
				mainResponseObject.getWishlists().get(0).setProducts(getProducts(customerServicesResponse, con));
			}
		}
	}
	
	private List<Product> getProducts(CustomerServicesResponse customerServicesResponse, Connection con) throws BaseException {
		MainRequestObject mainRequestObject = new MainRequestObject();
		GetProductsConfigurator getProductsConfigurator = new GetProductsConfigurator();
		MainResponseObject mainResponseObject = null;
		List<Integer> productIds = Utils.convertCommaSeparatedStringToIntegerList(customerServicesResponse.getWishlist().get(0).getWishlist());
		if (Utils.isNullOrEmptyCollection(productIds)) {
			return null;
		}
		mainRequestObject.setIds(productIds);
		mainResponseObject = getProductsConfigurator.getProducts(mainRequestObject, con);
		return mainResponseObject.getProducts();
	}
}
