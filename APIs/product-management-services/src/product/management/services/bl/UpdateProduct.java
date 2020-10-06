package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class UpdateProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UpdateProduct.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productsManagementRequest,  Connection connection) {
		ProductManagementResponse productsManagementResponse = null;
		
		productsManagementResponse = new ProductManagementResponse();
		if (null == productsManagementRequest.getProduct()) {
			productsManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			productsManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
			return productsManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? "Going to update user for user id: ": null);
		AbstractProductManagementServicesDao.getInstance().updateProduct(productsManagementRequest, connection);
		productsManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productsManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productsManagementResponse;
	}
}
