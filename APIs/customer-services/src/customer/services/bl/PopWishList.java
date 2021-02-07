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
		try {
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
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to get wishlist for username: [" +customerServicesRequest.getWishlist().getUsername()+ "]": null);
			customerServicesResponse = GetWishList.getInstance(CustomerServicesAction.GET_WISHLIST).customerServices(customerServicesRequest, connection);
			
			if (null == customerServicesResponse || null == customerServicesResponse.getWishlist() || Utils.isNullOrEmptyString(customerServicesResponse.getWishlist().get(0).getWishlist())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "No Need to pop anything from wishlish as no product found in wishlist ...: ": null);
				customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
				customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
				return customerServicesResponse;
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to create user for username: ": null);
			AbstractCustomerServicesDao.getInstance().popWishlist(customerServicesRequest, customerServicesResponse, connection);
			customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
			customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding queries (contactUs) ...");
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
