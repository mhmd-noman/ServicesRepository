package com.services.add.product.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.enums.ProductManagementServiceAction;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import product.management.services.ProductManagementService;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;

public class AddProductConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(AddProductConfigurator.class);
	
	public MainResponseObject addProduct(MainRequestObject mainRequestObject, Connection con) {
		MainResponseObject mainResponseObject = new MainResponseObject();
		ProductManagementService productManagementService = new ProductManagementService();
		ProductManagementRequest productManagementRequest = new ProductManagementRequest();
		ProductManagementResponse productManagementResponse = new ProductManagementResponse();
		mapRequest(mainRequestObject, productManagementRequest);
		productManagementResponse = productManagementService.productManagementService(productManagementRequest, con);
		mapResponse(mainResponseObject, productManagementResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, ProductManagementRequest productManagementRequest) {
		if (null != mainRequestObject && null != mainRequestObject.getProductInfo()) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for addProduct: [" +mainRequestObject+ "]": null);
			productManagementRequest.setProduct(mainRequestObject.getProductInfo());
		}
		productManagementRequest.setProductManagementServiceAction(ProductManagementServiceAction.ADD_PRODUCT);
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, ProductManagementResponse productManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for addProduct: [" +productManagementResponse+ "]": null);
		if (null != productManagementResponse) {
			mainResponseObject.setResponseCode(productManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(productManagementResponse.getResponseDesc());
			mainResponseObject.setProducts(productManagementResponse.getProducts());
		}
	}
}
