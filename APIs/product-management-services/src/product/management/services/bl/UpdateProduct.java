package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;
import product.management.services.utils.Constants;

public class UpdateProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UpdateProduct.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest,  Connection connection) throws BaseException {
		ProductManagementResponse productManagementResponse = null;
		try {
			productManagementResponse = new ProductManagementResponse();
			if (null == productManagementRequest.getProduct()) {
				productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return productManagementResponse;
			}
			if (Utils.validateIfNullOrInvalidInteger(productManagementRequest.getProduct().getId())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product id has been passed empty ...": null);
				productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productManagementResponse.setResponseDesc("Product id can't be null.");
				return productManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to update user for user id: ": null);
			AbstractProductManagementServicesDao.getInstance().updateProduct(productManagementRequest, connection);
			productManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			productManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while updating product ...");
			throw new BaseException(ex);
		}
		return productManagementResponse;
	}
}
