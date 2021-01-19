package customer.services.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Query;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.utils.Constants;
import database.manager.methods.AbstractCommonDbMethods;

public class CustomerServicesDaoImpl extends AbstractCustomerServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServicesDaoImpl.class);
	
	@Override
	public List<Query> getQueries(CustomerServicesRequest customerServicesRequest, Connection connection) {
		List<Object> paramList = null;
		List<Map<Integer, Object>> queriesResultSet = null;
		StringBuilder query = null;
		
		paramList = new ArrayList<>();
		query = new StringBuilder(AbstractCustomerServicesDao.GET_QUERIES);
		if (null != customerServicesRequest.getQuery()) {
			if (!Utils.validateIfNullOrInvalidInteger(customerServicesRequest.getQuery().getId())) {
				query.append(AbstractCustomerServicesDao.ID);
				paramList.add(customerServicesRequest.getQuery().getId());
			}
			if (!Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getPhone())) {
				query.append(AbstractCustomerServicesDao.PHONE);
				paramList.add(customerServicesRequest.getQuery().getPhone());
			}
			if (!Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getName())) {
				query.append(AbstractCustomerServicesDao.USERNAME);
				paramList.add(customerServicesRequest.getQuery().getName());
			}
			if (!Utils.validateIfNullOrEmptyString(customerServicesRequest.getQuery().getEmail())) {
				query.append(AbstractCustomerServicesDao.EMAIL);
				paramList.add(customerServicesRequest.getQuery().getEmail());
			}
		}
		
		logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch queries by using query: " +AbstractCustomerServicesDao.GET_QUERIES+ " with paramters: "+ paramList: null);
		queriesResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
		return prepareQueriesData(queriesResultSet);
	}
	
	private static List<Query> prepareQueriesData(List<Map<Integer, Object>> queriesResultSet) {
		List<Query> queries = new ArrayList<>();
		Query query = null;
		int index = 0;
		if (null != queriesResultSet) {
			for (Map<Integer, Object> userRow : queriesResultSet) {
				query = new Query();
				query.setId(null != userRow.get(++index) ? (Integer)userRow.get(index): null);
				query.setName(null != userRow.get(++index) ? (String)userRow.get(index): null);
				query.setEmail(null != userRow.get(++index) ? (String)userRow.get(index): null);
				query.setPhone(null != userRow.get(++index) ? (String)userRow.get(index): null);
				query.setMessage(null != userRow.get(++index) ? (String)userRow.get(index): null);
				queries.add(query);
				index = 0;
			}
		}
		return queries;
	}
	
	@Override
	public void contactUs(CustomerServicesRequest customerServicesRequest, Connection connection) {
		List<Object> paramList = null;
		if (null != customerServicesRequest) {
			paramList = new ArrayList<>();
			paramList.add(customerServicesRequest.getQuery().getName());
			paramList.add(customerServicesRequest.getQuery().getEmail());
			paramList.add(customerServicesRequest.getQuery().getPhone());
			paramList.add(customerServicesRequest.getQuery().getMessage());
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert query in contact_us by using query: " +AbstractCustomerServicesDao.CONTACT_US+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractCustomerServicesDao.CONTACT_US, paramList, connection);
		}
	}
}
