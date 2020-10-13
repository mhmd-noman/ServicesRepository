package com.services.get.product.configurator;

import java.sql.Connection;

import common.enums.ProductManagementServiceAction;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import product.management.services.ProductManagementService;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;

public class GetProductsConfigurator {
	public MainResponseObject getProducts(MainRequestObject mainRequestObject, Connection con) {
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
			productManagementRequest.setProduct(mainRequestObject.getProductInfo());
			productManagementRequest.setFetchOutOfStockProducts(mainRequestObject.isFetchOutOfStockProducts());
			productManagementRequest.setProductIds(mainRequestObject.getIds());
		}
		productManagementRequest.setProductManagementServiceAction(ProductManagementServiceAction.GET_PRODUCTS);
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, ProductManagementResponse productManagementResponse) {
		if (null != productManagementResponse) {
			mainResponseObject.setResponseCode(productManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(productManagementResponse.getResponseDesc());
			mainResponseObject.setProducts(productManagementResponse.getProducts());
		}
	}
}