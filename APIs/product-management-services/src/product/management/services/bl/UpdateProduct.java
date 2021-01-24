package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;
import product.management.services.utils.Constants;

public class UpdateProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UpdateProduct.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productsManagementRequest,  Connection connection) throws BaseException {
		ProductManagementResponse productsManagementResponse = null;
		try {
			productsManagementResponse = new ProductManagementResponse();
			if (null == productsManagementRequest.getProduct()) {
				productsManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productsManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return productsManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to update user for user id: ": null);
			AbstractProductManagementServicesDao.getInstance().updateProduct(productsManagementRequest, connection);
			productsManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			productsManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while updating product ...");
			throw new BaseException(ex);
		}
		return productsManagementResponse;
	}
}
