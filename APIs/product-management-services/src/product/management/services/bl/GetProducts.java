package product.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Product;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import product.management.services.beans.ProductManagementRequest;
import product.management.services.beans.ProductManagementResponse;
import product.management.services.dao.AbstractProductManagementServicesDao;
import product.management.services.utils.Constants;

public class GetProducts extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetProducts.class);

	public ProductManagementResponse productManagementService(ProductManagementRequest productsManagementRequest,  Connection connection) throws BaseException {
		ProductManagementResponse productsManagementResponse = null;
		List<Product> productsList = null;
		try {
			productsManagementResponse = new ProductManagementResponse();
			productsList = new ArrayList<>();
			
			if (null == productsManagementRequest.getProduct() && Utils.isNullOrEmptyCollection(productsManagementRequest.getProductIds())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "No content has been provided in request for getProducts ... ": null);
				productsManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productsManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return productsManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to create product for username: ": null);
			productsList = AbstractProductManagementServicesDao.getInstance().getProducts(productsManagementRequest, connection);
			if (!Utils.isNullOrEmptyCollection(productsList)) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Retrieved Products: "+ productsList: null);
				if (productsManagementRequest.isReturnCount()) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to return count of products as well as isReturnCount found: [" +productsManagementRequest.isReturnCount()+ "]": null);
					productsManagementResponse.setProductsCount(productsList.size());
				}
				productsManagementResponse.setProducts(Utils.pagination(productsList, productsManagementRequest.getPageNo(), productsManagementRequest.getPageSize()));
			}
			productsManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			productsManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting products ..."+ ex);
			throw new BaseException(ex);
		}
		return productsManagementResponse;
	}
}
