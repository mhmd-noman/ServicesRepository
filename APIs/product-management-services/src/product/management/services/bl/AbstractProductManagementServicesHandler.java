package product.management.services.bl;

import java.sql.Connection;

import common.enums.ProductManagementServiceAction;
import common.exception.handling.BaseException;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;

public abstract class AbstractProductManagementServicesHandler {
	public static AbstractProductManagementServicesHandler getInstance() throws BaseException {
		return new GetProducts();
	}

	public static AbstractProductManagementServicesHandler getInstance(ProductManagementServiceAction productManagementServiceAction) throws BaseException {
		AbstractProductManagementServicesHandler abstractManagementProductsHandler = null;
		switch(productManagementServiceAction) {
		case GET_PRODUCTS:
			abstractManagementProductsHandler = new GetProducts();
			break;
		case ADD_PRODUCT:
			abstractManagementProductsHandler = new AddProduct();
			break;
		case UPDATE_PRODUCT:
			abstractManagementProductsHandler = new UpdateProduct();
			break;
		case DELETE_PRODUCT:
			abstractManagementProductsHandler = new DeleteProduct();
			break;
		default:
			abstractManagementProductsHandler = new GetProducts();
			break;
		}
		return abstractManagementProductsHandler;
	}

	public abstract ProductManagementResponse productManagementService(ProductManagementRequest usersManagementRequest,  Connection connection) throws BaseException;
}