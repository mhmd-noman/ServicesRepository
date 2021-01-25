package user.management.services.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.User;
import common.exception.handling.BaseException;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import database.manager.methods.AbstractCommonDbMethods;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.utils.Constants;

public class UserManagementServicesDaoImpl extends AbstractUserManagementServicesDao {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Override
	public List<User> getUsers(UsersManagementRequest usersManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> usersResultSet = null;
		StringBuilder query = null;
		try {
			paramList = new ArrayList<>();
			query = new StringBuilder(AbstractUserManagementServicesDao.GET_USERS);
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				query.append(AbstractUserManagementServicesDao.USERNAME);
				paramList.add(usersManagementRequest.getUsername());
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getFirstName())) {
				query.append(AbstractUserManagementServicesDao.FIRST_NAME);
				paramList.add(usersManagementRequest.getFirstName());
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getLastName())) {
				query.append(AbstractUserManagementServicesDao.LAST_NAME);
				paramList.add(usersManagementRequest.getLastName());
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getPhone())) {
				query.append(AbstractUserManagementServicesDao.PHONE_NUMBER);
				paramList.add(usersManagementRequest.getPhone());
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getEmail())) {
				query.append(AbstractUserManagementServicesDao.EMAIL);
				paramList.add(usersManagementRequest.getEmail());
			}
			//if (!Utils.isNullOrEmptyCollection(paramList)) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch users by using query: " +AbstractUserManagementServicesDao.GET_USERS+ " with paramters: "+ paramList: null);
			usersResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
			//}
		} catch (Exception ex) {
			logger.warn("##Exception## while getting users data ...");
			throw new BaseException(ex);
		}
		return prepareUsersData(usersResultSet);
	}
	
	private static List<User> prepareUsersData(List<Map<Integer, Object>> usersResultSet) throws BaseException {
		List<User> users = new ArrayList<>();
		User user = null;
		int index = 0;
		try {
			if (null != usersResultSet) {
				for (Map<Integer, Object> userRow : usersResultSet) {
					user = new User();
					user.setSerialNumber(null != userRow.get(++index) ? (Integer)userRow.get(index): null);
					user.setEmail(null != userRow.get(++index) ? (String)userRow.get(index): null);
					user.setEnabled(null != userRow.get(++index) ? (boolean)userRow.get(index) == true ? CommonConstants.OPTION_Y: CommonConstants.OPTION_N: null);
					user.setFirstName(null != userRow.get(++index) ? (String)userRow.get(index): null);
					user.setLastName(null != userRow.get(++index) ? (String)userRow.get(index): null);
					user.setPassword(null != userRow.get(++index) ? (String)userRow.get(index): null);
					user.setPhoneNumber(null != userRow.get(++index) ? (String)userRow.get(index): null);
					user.setUsername(null != userRow.get(++index) ? (String)userRow.get(index): null);
					users.add(user);
					index = 0;
				}
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while preparing user data ...");
			throw new BaseException(ex);
		}	
		return users;
	}
	
	@Override
	public void createUser(UsersManagementRequest usersManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != usersManagementRequest) {
				paramList = new ArrayList<>();
				paramList.add(usersManagementRequest.getEmail());
				paramList.add(Constants.OPTION_Y.equalsIgnoreCase(usersManagementRequest.getEnabled()));
				paramList.add(usersManagementRequest.getFirstName());
				paramList.add(usersManagementRequest.getLastName());
				paramList.add(usersManagementRequest.getPassword());
				paramList.add(usersManagementRequest.getPhone());
				paramList.add(usersManagementRequest.getUsername());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to insert user by using query: " +AbstractUserManagementServicesDao.CREATE_USER+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractUserManagementServicesDao.CREATE_USER, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while creating user ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public void updateUser(UsersManagementRequest usersManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		StringBuilder query = null;
		StringBuilder updateColumns = new StringBuilder();
		try {
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getEmail())) {
				updateColumns.append("email = '" +usersManagementRequest.getEmail()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getEnabled())) {
				updateColumns.append("enabled = " +Constants.OPTION_Y.equalsIgnoreCase(usersManagementRequest.getEnabled())+ ",");
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getFirstName())) {
				updateColumns.append("first_name = '" +usersManagementRequest.getFirstName()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getLastName())) {
				updateColumns.append("last_name = '" +usersManagementRequest.getLastName()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getPassword())) {
				updateColumns.append("password = '" +usersManagementRequest.getPassword()+ "',");
			}
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getPhone())) {
				updateColumns.append("phone = '" +usersManagementRequest.getPhone()+ "',");
			}
			
			if (!Utils.validateIfNullOrEmptyString(updateColumns.toString().toString())) {
				query = new StringBuilder("update users set " +updateColumns.toString().substring(0, updateColumns.length() - 1)+ " where username = '" +usersManagementRequest.getUsername()+ "'");
				logger.debug(logger.isDebugEnabled() ? Constants.SERVICE_NAME + "Going to insert user by using query: " +query.toString()+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(query.toString(), paramList, connection);	
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while updating user ...");
			throw new BaseException(ex);
		}	
	}

	@Override
	public void deleteUser(UsersManagementRequest usersManagementRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				paramList = new ArrayList<>();
				paramList.add(usersManagementRequest.getUsername());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to delete user by using query: " +AbstractUserManagementServicesDao.DELETE_USER+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractUserManagementServicesDao.DELETE_USER, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while deleting user ...");
			throw new BaseException(ex);
		}
	}
	
	@Override
	public List<User> getUserIfUserAlreadyExists(String username, String phone, String email, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> usersResultSet = null;
		StringBuilder query = null;
		try {
			paramList = new ArrayList<>();
			query = new StringBuilder(AbstractUserManagementServicesDao.VALIDATE_IF_USER_ALREADY_EXISTS);
			if (!Utils.validateIfNullOrEmptyString(username)) {
				paramList.add(username);
			}
			if (!Utils.validateIfNullOrEmptyString(phone)) {
				query.append(AbstractUserManagementServicesDao.OR_PHONE_NUMBER);
				paramList.add(phone);
			}
			if (!Utils.validateIfNullOrEmptyString(email)) {
				query.append(AbstractUserManagementServicesDao.OR_EMAIL);
				paramList.add(email);
			}
			//if (!Utils.isNullOrEmptyCollection(paramList)) {
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to fetch users by using query: " +AbstractUserManagementServicesDao.VALIDATE_IF_USER_ALREADY_EXISTS+ " with paramters: "+ paramList: null);
			usersResultSet = AbstractCommonDbMethods.getInstance().select(query.toString(), paramList, connection);	
			return prepareUsersData(usersResultSet);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting users data ...");
			throw new BaseException(ex);
		}
	}
}
