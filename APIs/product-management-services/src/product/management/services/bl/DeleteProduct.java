package product.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class DeleteProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(DeleteProduct.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productsManagementRequest, Connection connection) {
		ProductManagementResponse productsManagementResponse = null;
		
		productsManagementResponse = new ProductManagementResponse();
		if (null == productsManagementRequest.getProduct()
				&& !Utils.validateIfNullOrEmptyString(productsManagementRequest.getProduct().getId())) {
		}
		logger.info(logger.isInfoEnabled() ? "Going to delete product for user id: ": null);
		AbstractProductManagementServicesDao.getInstance().deleteProduct(productsManagementRequest, connection);
		productsManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productsManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productsManagementResponse;
	}
}
