package product.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Product;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;

public class GetProducts extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetProducts.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productsManagementRequest,  Connection connection) {
		ProductManagementResponse productsManagementResponse = null;
		List<Product> productsList = null;

		productsManagementResponse = new ProductManagementResponse();
		productsList = new ArrayList<>();
		
//		if (null == productsManagementRequest.getProduct()) {
//			productsManagementResponse.setResponseCode(CommonConstants.INVALID_TRANS);
//			productsManagementResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
//			return productsManagementResponse;
//		}
		logger.info(logger.isInfoEnabled() ? "Going to create product for username: ": null);
		productsList = AbstractProductManagementServicesDao.getInstance().getProducts(productsManagementRequest, connection);
		if (!Utils.isNullOrEmptyCollection(productsList)) {
			logger.info(logger.isInfoEnabled() ? "Retrieved Users: "+ productsList: null);
			productsManagementResponse.setProducts(productsList);
		}
		productsManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		productsManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return productsManagementResponse;
	}
}
