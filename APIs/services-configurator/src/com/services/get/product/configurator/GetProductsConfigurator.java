package com.services.get.product.configurator;

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

public class GetProductsConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(GetProductsConfigurator.class);

	public MainResponseObject getProducts(MainRequestObject mainRequestObject, Connection con) throws BaseException {
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
		if (null != mainRequestObject) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for getProducts: [" +mainRequestObject+ "]": null);
			productManagementRequest.setProduct(mainRequestObject.getProductInfo());
			productManagementRequest.setProductIds(mainRequestObject.getIds());
			productManagementRequest.setFetchOutOfStockProducts(mainRequestObject.isFetchOutOfStockProducts());
			productManagementRequest.setFetchProductsWithDiscountOnly(mainRequestObject.isFetchProductsWithDiscountOnly());
			productManagementRequest.setPageNo(mainRequestObject.getPageNo());
			productManagementRequest.setPageSize(mainRequestObject.getPageSize());
			mainRequestObject.setServiceId(ProductManagementServiceAction.GET_PRODUCTS.toString());
		}
		productManagementRequest.setProductManagementServiceAction(ProductManagementServiceAction.GET_PRODUCTS);
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, ProductManagementResponse productManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for getProducts: [" +productManagementResponse+ "]": null);
		if (null != productManagementResponse) {
			mainResponseObject.setResponseCode(productManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(productManagementResponse.getResponseDesc());
			mainResponseObject.setProducts(productManagementResponse.getProducts());
		}
	}
}
