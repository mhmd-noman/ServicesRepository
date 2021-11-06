package customer.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.dao.AbstractCustomerServicesDao;
import customer.services.utils.Constants;

public class ContactUs extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(ContactUs.class);
	
	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) throws BaseException {
		CustomerServicesResponse customerServicesResponse = null;
		try {
			customerServicesResponse = new CustomerServicesResponse();
			if (null == customerServicesRequest || null == customerServicesRequest.getQuery()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "No content has been passed in contactUs Request ...: ": null);
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return customerServicesResponse;
			}
			if (Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getName())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Name should be passed for contactUs Request ...: ": null);
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("Name has been passed Empty!");
				return customerServicesResponse;
			}
			if (Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getEmail()) && Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getPhone())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Email and Phone both can't be null/empty for contactUs Request ...: ": null);
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("Email/Phone has been passed Empty!");
				return customerServicesResponse;
			}
			if (Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getMessage())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Message should be passed for contactUs Request ...: ": null);
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc("Message has been passed Empty!");
				return customerServicesResponse;
			}
	
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to create user for username: ": null);
			AbstractCustomerServicesDao.getInstance().contactUs(customerServicesRequest, connection);
			customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
			customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while adding queries (contactUs) ..."+ex);
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
