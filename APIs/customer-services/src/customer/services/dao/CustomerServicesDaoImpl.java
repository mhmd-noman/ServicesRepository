package customer.services.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.Query;
import common.beans.Wishlist;
import common.exception.handling.BaseException;
import common.utilities.methods.Utils;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.utils.Constants;
import database.manager.methods.AbstractCommonDbMethods;

public class CustomerServicesDaoImpl extends AbstractCustomerServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServicesDaoImpl.class);
	
	@Override
	public List<Query> getQueries(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> queriesResultSet = null;
		StringBuilder query = null;
		try {
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
		} catch (Exception ex) {
			logger.warn("##Exception## in getting queries service ...");
			throw new BaseException(ex);
		}
		return prepareQueriesData(queriesResultSet);
	}
	
	private static List<Query> prepareQueriesData(List<Map<Integer, Object>> queriesResultSet) throws BaseException {
		List<Query> queries = new ArrayList<>();
		Query query = null;
		int index = 0;
		try {
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
		} catch (Exception ex) {
			logger.warn("##Exception## in preparing queries data ...");
			throw new BaseException(ex);
		}
		return queries;
	}
	
	@Override
	public void contactUs(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != customerServicesRequest) {
				paramList = new ArrayList<>();
				paramList.add(customerServicesRequest.getQuery().getName());
				paramList.add(customerServicesRequest.getQuery().getEmail());
				paramList.add(customerServicesRequest.getQuery().getPhone());
				paramList.add(customerServicesRequest.getQuery().getMessage());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert query in contact_us by using query: " +AbstractCustomerServicesDao.CONTACT_US+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractCustomerServicesDao.CONTACT_US, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## in adding query ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public List<Wishlist> getWishlist(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> wishlistsResultSet = null;
		StringBuilder query = null;
		try {
			paramList = new ArrayList<>();
			
			if (null != customerServicesRequest.getWishlist() && Utils.isValidString(customerServicesRequest.getWishlist().getUsername())) {
				query = new StringBuilder(AbstractCustomerServicesDao.GET_WISHLIST);
				paramList.add(customerServicesRequest.getWishlist().getUsername());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch queries by using query: " +AbstractCustomerServicesDao.GET_QUERIES+ " with paramters: "+ paramList: null);
				wishlistsResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
			}
		} catch (Exception ex) {
			logger.warn("##Exception## in getting queries service ...");
			throw new BaseException(ex);
		}
		return prepareWishlistData(wishlistsResultSet);
	}
	
	private static List<Wishlist> prepareWishlistData(List<Map<Integer, Object>> wishlistsResultSet) throws BaseException {
		List<Wishlist> wishlists = new ArrayList<>();
		Wishlist wishlist = null;
		int index = 0;
		try {
			if (null != wishlistsResultSet) {
				for (Map<Integer, Object> userRow : wishlistsResultSet) {
					wishlist = new Wishlist();
					wishlist.setUsername(null != userRow.get(++index) ? (String)userRow.get(index): null);
					wishlist.setWishlist(null != userRow.get(++index) ? (String)userRow.get(index): null);
					wishlists.add(wishlist);
					index = 0;
				}
			}
		} catch (Exception ex) {
			logger.warn("##Exception## in preparing wishlist data ...");
			throw new BaseException(ex);
		}
		return wishlists;
	}
	
	@Override
	public void pushWishlist(CustomerServicesRequest customerServicesRequest, CustomerServicesResponse customerServicesResponse, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != customerServicesResponse && Utils.isNullOrEmptyCollection(customerServicesResponse.getWishlist())) {
				if (null != customerServicesRequest) {
					paramList = new ArrayList<>();
					paramList.add(customerServicesRequest.getWishlist().getUsername());
					paramList.add(customerServicesRequest.getWishlist().getWishlist());
					customerServicesResponse.setWishlist(new ArrayList<>());
					customerServicesResponse.getWishlist().add(new Wishlist());
					customerServicesResponse.getWishlist().get(0).setWishlist(customerServicesRequest.getWishlist().getWishlist());
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert wishlist in wishlists by using query: " +AbstractCustomerServicesDao.INSERT_WISHLIST+ " with paramters: "+ paramList: null);
					AbstractCommonDbMethods.getInstance().update(AbstractCustomerServicesDao.INSERT_WISHLIST, paramList, connection);
					return;
				}
			}
			paramList = new ArrayList<>();
			paramList.add(","+customerServicesRequest.getWishlist().getWishlist());
			paramList.add(Utils.getCurrentDateTime());
			paramList.add(customerServicesRequest.getWishlist().getUsername());
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to push wishlist in wishlists by using query: " +AbstractCustomerServicesDao.PUSH_WISHLIST+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractCustomerServicesDao.PUSH_WISHLIST, paramList, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while pushing wishlist ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public void popWishlist(CustomerServicesRequest customerServicesRequest, CustomerServicesResponse customerServicesResponse, Connection connection) throws BaseException {
		List<Object> paramList = null;
		String [] wishlist = null;
		String wishlistToUpdate = null;
		try {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to get comma separated string of wishlist into string array": null);
			wishlist = Utils.convertCommaSeparatedStringToStringArray(customerServicesResponse.getWishlist().get(0).getWishlist());
			
			for (String product : wishlist) {
				if (product.equalsIgnoreCase(customerServicesRequest.getWishlist().getWishlist())) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to pop product from wishlist: [" +product+ "]": null);
					continue;
				}
				if (Utils.isNullOrEmptyString(wishlistToUpdate)) {
					wishlistToUpdate = product;
					continue;
				}
				wishlistToUpdate = wishlistToUpdate +","+ product;
			}
			customerServicesResponse.getWishlist().get(0).setWishlist(wishlistToUpdate);
			if (null != customerServicesRequest) {
				paramList = new ArrayList<>();
				paramList.add(wishlistToUpdate);
				paramList.add(Utils.getCurrentDateTime());
				paramList.add(customerServicesRequest.getWishlist().getUsername());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to pop wishlist in wishlists by using query: " +AbstractCustomerServicesDao.UPDATE_WISHLIST+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractCustomerServicesDao.UPDATE_WISHLIST, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while poping wishlist ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public boolean ifUserExists(String username, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> userResultSet = null;
		StringBuilder query = null;
		try {
			paramList = new ArrayList<>();
			
			if (Utils.isValidString(username)) {
				query = new StringBuilder(AbstractCustomerServicesDao.VERIFY_IF_USER_EXISTS);
				paramList.add(username);
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to check if user exists using query: " +AbstractCustomerServicesDao.VERIFY_IF_USER_EXISTS+ " with paramters: "+ paramList: null);
				userResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
			}
			if (Utils.isNullOrEmptyCollection(userResultSet)) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User doesn't exist against passed username with paramters: "+ paramList: null);
				return false;
			} else {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User found against passed username with paramters: "+ paramList: null);
				return true;
			}
		} catch (Exception ex) {
			logger.warn("##Exception## in getting queries service ...");
			throw new BaseException(ex);
		}
	}
}
