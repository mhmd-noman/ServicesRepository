package com.services.delete.product.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.enums.ProductManagementServiceAction;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import product.management.services.ProductManagementService;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;

public class DeleteProductConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(DeleteProductConfigurator.class);
	
	public MainResponseObject deleteProduct(MainRequestObject mainRequestObject, Connection con) throws BaseException {
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
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for deleteProduct: [" +mainRequestObject+ "]": null);
			productManagementRequest.setProduct(mainRequestObject.getProductInfo());
		}
		productManagementRequest.setProductManagementServiceAction(ProductManagementServiceAction.DELETE_PRODUCT);
		mainRequestObject.setServiceId(ProductManagementServiceAction.DELETE_PRODUCT.toString());
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, ProductManagementResponse productManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for deleteProduct: [" +productManagementResponse+ "]": null);
		if (null != productManagementResponse) {
			mainResponseObject.setResponseCode(productManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(productManagementResponse.getResponseDesc());
			mainResponseObject.setProducts(productManagementResponse.getProducts());
		}
	}
}
