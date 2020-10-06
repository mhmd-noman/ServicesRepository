package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class AddProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(AddProduct.class);
	
	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest,  Connection connection) {
		ProductManagementResponse productManagementResponse = null;
		
		productManagementResponse = new ProductManagementResponse();
		if (null != productManagementRequest.getProduct()) {
			productManagementResponse = new ProductManagementResponse();
			logger.info(logger.isInfoEnabled() ? "Going to add product for username: ": null);
		}
		AbstractProductManagementServicesDao.getInstance().addProduct(productManagementRequest, connection);
		productManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productManagementResponse;
	}
}
