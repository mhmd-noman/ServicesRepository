/**
* @author  Muhammad Noman
* @version 1.0
* @since   2021-January-19
*/
package customer.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import customer.services.utils.Constants;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.bl.AbstractCustomerServicesHandler;

public class CustomerServices {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServices.class);
	
	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest, Connection con) {
		CustomerServicesResponse customerServicesResponse = null;
		if (null == customerServicesRequest) {
			return customerServicesResponse;
		}
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to call CustomerServices ...": null);
		customerServicesResponse = AbstractCustomerServicesHandler.getInstance(customerServicesRequest.getCustomerServicesAction()).customerServices(customerServicesRequest, con);
		return customerServicesResponse;
	}
}
