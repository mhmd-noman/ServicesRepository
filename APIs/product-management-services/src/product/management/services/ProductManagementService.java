/**
* @author  Muhammad Noman
* @version 1.0
* @since   2020-September-06 
*/
package product.management.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.bl.AbstractProductManagementServicesHandler;

public class ProductManagementService {
	private static final Logger logger = LoggerFactory.getLogger(ProductManagementService.class);
	
	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest, Connection con) throws BaseException {
		ProductManagementResponse productManagementResponse = new ProductManagementResponse();
		try {
			if (null == productManagementRequest) {
				productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productManagementResponse.setResponseDesc("Product Request Content has been Passed Empty.");
				return productManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? "Going to call productManagementService Service": null);
			productManagementResponse = AbstractProductManagementServicesHandler.getInstance(productManagementRequest.getProductManagementServiceAction()).productManagementService(productManagementRequest, con);
		} catch (Exception ex) {
			logger.warn("##Exception## in product management service ..."+ex);
			throw new BaseException(ex);
		}
		return productManagementResponse;
	}
}
