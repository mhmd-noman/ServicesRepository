package customer.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Query;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.dao.AbstractCustomerServicesDao;

public class GetQueries extends AbstractCustomerServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(GetQueries.class);

	public CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) throws BaseException {
		CustomerServicesResponse customerServicesResponse = null;
		List<Query> queriesList = null;
		try {
			customerServicesResponse = new CustomerServicesResponse();
			queriesList = new ArrayList<>();
			if (null == customerServicesRequest) {
				customerServicesResponse = new CustomerServicesResponse();
				customerServicesResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				customerServicesResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return customerServicesResponse;
			}
			queriesList = AbstractCustomerServicesDao.getInstance().getQueries(customerServicesRequest, connection);
			if (!Utils.isNullOrEmptyCollection(queriesList)) {
				logger.info(logger.isInfoEnabled() ? "Retrieved Queries: "+ queriesList: null);
				customerServicesResponse.setQueries(queriesList);
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
