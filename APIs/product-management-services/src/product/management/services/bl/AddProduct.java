package product.management.services.bl;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Product;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class AddProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(AddProduct.class);
	
	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest, Connection connection) {
		ProductManagementResponse productManagementResponse = null;
		productManagementResponse = new ProductManagementResponse();
		if (null == productManagementRequest || null == productManagementRequest.getProduct()) {
			logger.info(logger.isInfoEnabled() ? "Couldn't proceed as product requested content has been passed empty ...": null);
			productManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			productManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
			return productManagementResponse;
		}
		if(ifProductAlreadyExist(productManagementRequest, connection)) {
			productManagementResponse.setResponseCode(CommonConstants.PRODUCT_ALREADY_EXISTS);
			productManagementResponse.setResponseDesc(CommonConstants.PRODUCT_ALREADY_EXISTS_DESCRIPTION);
			return productManagementResponse;
		}
		AbstractProductManagementServicesDao.getInstance().addProduct(productManagementRequest, connection);
		productManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productManagementResponse;
	}
	
	private boolean ifProductAlreadyExist(ProductManagementRequest productManagementRequest, Connection connection) {
		logger.info(logger.isInfoEnabled() ? "Going to check if requested product is already existed ...": null);
		productManagementRequest.setFetchOutOfStockProducts(true);
		List<Product> products = AbstractProductManagementServicesDao.getInstance().getProducts(productManagementRequest, connection);
		if (!Utils.isNullOrEmptyCollection(products)) {
			logger.info(logger.isInfoEnabled() ? "Requested product already exist ...": null);
			return true;
		}
		return false;
	}
}
