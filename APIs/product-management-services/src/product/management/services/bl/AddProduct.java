package product.management.services.bl;

import java.sql.Connection;
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

public class AddProduct extends AbstractProductManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(AddProduct.class);
	
	public ProductManagementResponse productManagementService(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException {
		ProductManagementResponse productManagementResponse = null;
		productManagementResponse = new ProductManagementResponse();
		try {
			if (null == productManagementRequest || null == productManagementRequest.getProduct()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product requested content has been passed empty ...": null);
				productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				productManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return productManagementResponse;
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to validate mendatory infos ...": null);
			validateMandatoryInfos(productManagementRequest, productManagementResponse);
			if (!ResponseCodes.SUCCESS.equalsIgnoreCase(productManagementResponse.getResponseCode())) {
				return productManagementResponse;
			}
			
			if(ifProductAlreadyExist(productManagementRequest, connection)) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Requested product already exists in database ...": null);
				productManagementResponse.setResponseCode(ResponseCodes.PRODUCT_ALREADY_EXISTS);
				productManagementResponse.setResponseDesc(ResponseCodes.PRODUCT_ALREADY_EXISTS_DESCRIPTION);
				return productManagementResponse;
			}
			AbstractProductManagementServicesDao.getInstance().addProduct(productManagementRequest, connection);
			productManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			productManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
			return productManagementResponse;
		} catch (Exception ex) {
			logger.warn("##Exception## while adding product ...");
			throw new BaseException(ex);
		}
	}
	
	private void validateMandatoryInfos(ProductManagementRequest productManagementRequest, ProductManagementResponse productManagementResponse) {
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getName())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product name has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product name can't be null.");
			return;
		}
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getFlavour())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product flavor has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product flavor can't be null.");
			return;
		}
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getCategory())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product category has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product category can't be null.");
			return;
		}
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getCompany())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product company has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product company can't be null.");
			return;
		}
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getServings())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product org price has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product servings can't be null.");
			return;
		}
		if (Utils.validateIfNullOrEmptyString(productManagementRequest.getProduct().getWeight())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product weight has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product weight can't be null.");
			return;
		}
		if (Utils.validateIfNullOrInvalidInteger(productManagementRequest.getProduct().getQuantity())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product quantity has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product quantity can't be null.");
			return;
		}
		if (Utils.validateIfNullOrInvalidDouble(productManagementRequest.getProduct().getOrgPrice())) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product org price has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product org price can't be null.");
			return;
		}
		if (null == productManagementRequest.getProduct().getMfgDate() || null == productManagementRequest.getProduct().getExpiryDate()) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Couldn't proceed as product mfg/expiry has been passed empty ...": null);
			productManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			productManagementResponse.setResponseDesc("Product expiry/manufacturing date can't be null.");
			return;
		}
		productManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
		productManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
	}
	
	private boolean ifProductAlreadyExist(ProductManagementRequest productManagementRequest, Connection connection) throws BaseException {
		logger.info(logger.isInfoEnabled() ? "Going to check if requested product is already existed ...": null);
		ProductManagementRequest productManagementReqst = new ProductManagementRequest();
		productManagementReqst.setFetchOutOfStockProducts(true);
		productManagementReqst.setIfCallingFromAddProduct(true);
		productManagementReqst.setProduct(new Product());
		productManagementReqst.getProduct().setName(productManagementRequest.getProduct().getName());
		productManagementReqst.getProduct().setFlavour(productManagementRequest.getProduct().getFlavour());
		productManagementReqst.getProduct().setWeight(productManagementRequest.getProduct().getWeight());
		logger.info(logger.isInfoEnabled() ? "Going to call getProducts Service from addProduct Service ...": null);
		List<Product> products = AbstractProductManagementServicesDao.getInstance().getProducts(productManagementReqst, connection);
		if (!Utils.isNullOrEmptyCollection(products)) {
			logger.info(logger.isInfoEnabled() ? "Requested product already exist ...": null);
			return true;
		}
		return false;
	}
}
