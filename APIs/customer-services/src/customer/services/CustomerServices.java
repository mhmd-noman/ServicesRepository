/**
* @author  Muhammad Noman
* @version 1.0
* @since   2021-January-19
*/
package customer.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.bl.AbstractCustomerServicesHandler;
import customer.services.utils.Constants;

public class CustomerServices {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServices.class);
	
	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest, Connection con) throws BaseException {
		CustomerServicesResponse customerServicesResponse = null;
		try {
			if (null == customerServicesRequest) {
				return customerServicesResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to call CustomerServices ...": null);
			customerServicesResponse = AbstractCustomerServicesHandler.getInstance(customerServicesRequest.getCustomerServicesAction()).customerServices(customerServicesRequest, con);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting queries ..."+ex);
			throw new BaseException(ex);
		}
		return customerServicesResponse;
	}
}
