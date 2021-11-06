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

public class PushWishList extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(PushWishList.class);
	
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
			
			if (!Utils.isNullOrEmptyCollection(customerServicesResponse.getWishlist())
					&& customerServicesResponse.getWishlist().get(0).getWishlist().contains(customerServicesRequest.getWishlist().getWishlist())) {
				customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
				customerServicesResponse.setResponseDesc("Already exists in Wishlist!");
				return customerServicesResponse;
			}
			if (null != customerServicesResponse && !Utils.isNullOrEmptyCollection(customerServicesResponse.getWishlist())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to persist wishlist for username: [" +customerServicesRequest.getWishlist().getUsername()+ "] in order to get complete products in response": null);
				customerServicesResponse.getWishlist().get(0).setWishlist(customerServicesResponse.getWishlist().get(0).getWishlist() +","+ customerServicesRequest.getWishlist().getWishlist());
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to push wishlist for username: [" +customerServicesRequest.getWishlist().getUsername()+ "]": null);
			dao.pushWishlist(customerServicesRequest, customerServicesResponse, connection);
			customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
			customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding queries (contactUs) ..."+ex);
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
