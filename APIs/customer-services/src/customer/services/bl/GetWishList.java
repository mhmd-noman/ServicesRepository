package customer.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Wishlist;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.dao.AbstractCustomerServicesDao;

public class GetWishList extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetWishList.class);

	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) throws BaseException {
		CustomerServicesResponse customerServicesResponse = null;
		List<Wishlist> wishlists = null;
		try {
			customerServicesResponse = new CustomerServicesResponse();
			wishlists = new ArrayList<>();
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

			wishlists = AbstractCustomerServicesDao.getInstance().getWishlist(customerServicesRequest, connection);
			if (!Utils.isNullOrEmptyCollection(wishlists)) {
				logger.info(logger.isInfoEnabled() ? "Retrieved Wishlist: "+ wishlists: null);
				customerServicesResponse.setWishlist(wishlists);
			}
			customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
			customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting queries ..."+ex);
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
