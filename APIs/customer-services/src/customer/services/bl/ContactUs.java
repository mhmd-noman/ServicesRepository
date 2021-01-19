package customer.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.ResponseCodes;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.dao.AbstractCustomerServicesDao;
import customer.services.utils.Constants;

public class ContactUs extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(ContactUs.class);
	
	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) {
		CustomerServicesResponse customerServicesResponse = null;
		customerServicesResponse = new CustomerServicesResponse();
		if (null == customerServicesRequest) {
			customerServicesResponse = new CustomerServicesResponse();
			customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
			customerServicesResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
			return customerServicesResponse;
		}
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to create user for username: ": null);
		AbstractCustomerServicesDao.getInstance().contactUs(customerServicesRequest, connection);
		customerServicesResponse.setResponseCode(ResponseCodes.SUCCESS);
		customerServicesResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		return customerServicesResponse;
	}

}
