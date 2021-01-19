package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.ResponseCodes;
import product.management.services.utils.Constants;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class DeleteProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(DeleteProduct.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest, Connection connection) {
		ProductManagementResponse productManagementResponse = null;	
		productManagementResponse = new ProductManagementResponse();
		if (null == productManagementRequest || null == productManagementRequest.getProduct()) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product requested content has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
			return productManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to delete product for user id: ": null);
		AbstractProductManagementServicesDao.getInstance().deleteProduct(productManagementRequest, connection);
		productManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
		productManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		return productManagementResponse;
	}
}
