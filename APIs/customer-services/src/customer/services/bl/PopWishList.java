package customer.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.enums.CustomerServicesAction;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.dao.AbstractCustomerServicesDao;
import customer.services.utils.Constants;

public class PopWishList extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(PopWishList.class);
	
	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) throws BaseException {
		CustomerServicesResponse customerServicesResponse = null;
		AbstractCustomerServicesDao dao = null;
		try {
			dao = AbstractCustomerServicesDao.getInstance();
			customerServicesResponse = new CustomerServicesResponse();

			if (null == customerServicesRequest || null == customerServicesRequest.getWishlist()) {
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return customerServicesResponse;
			}
			
			if (Utils.validateIfNullOrEmptyString(customerServicesRequest.getWishlist().getUsername())) {
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("Username is Mendatory!");
				return customerServicesResponse;
			}
			
			if (Utils.validateIfNullOrEmptyString(customerServicesRequest.getWishlist().getWishlist())) {
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("Wishlist is Mendatory!");
				return customerServicesResponse;
			}
			
			if (!dao.ifUserExists(customerServicesRequest.getWishlist().getUsername(), connection)) {
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.USER_NOT_EXISTS);
				customerServicesResponse.setResponseDesc(ResponseCodes.USER_NOT_EXISTS_DESCRIPTION);
				return customerServicesResponse;
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to get wishlist for username: [" +customerServicesRequest.getWishlist().getUsername()+ "]": null);
			customerServicesResponse = GetWishList.getInstance(CustomerServicesAction.GET_WISHLIST).customerServices(customerServicesRequest, connection);
			
			if (null == customerServicesResponse || Utils.isNullOrEmptyCollection(customerServicesResponse.getWishlist()) || Utils.isNullOrEmptyString(customerServicesResponse.getWishlist().get(0).getWishlist())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "No Need to pop anything from wishlish as no product found in wishlist ... ": null);
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("No Wishlist found against provided User!");
				return customerServicesResponse;
			}
			
			if (!customerServicesRequest.getWishlist().getWishlist().contains(customerServicesResponse.getWishlist().get(0).getWishlist())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "No Need to pop from wishlish as product already doesn't exist in wishlist ... ": null);
				customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
				customerServicesResponse.setResponseDesc("Product not found in wishlist!");
				return customerServicesResponse;
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going pop product from wishlist: ": null);
			dao.popWishlist(customerServicesRequest, customerServicesResponse, connection);
			customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
			customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding queries (contactUs) ..."+ex);
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
