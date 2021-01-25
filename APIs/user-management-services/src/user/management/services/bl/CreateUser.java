package user.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.User;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.dao.AbstractUserManagementServicesDao;
import user.management.services.utils.Constants;

public class CreateUser extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection) throws BaseException {
		UsersManagementResponse usersManagementResponse = null;
		try {
			usersManagementResponse = new UsersManagementResponse();
			if (null == usersManagementRequest) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for createUser ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			if (Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Username can't be null/empty in request ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			if (Utils.validateIfNullOrEmptyString(usersManagementRequest.getPhone()) || Utils.validateIfNullOrEmptyString(usersManagementRequest.getEmail())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Phone/Email both can't be null/empty in request ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			
			if (Utils.validateIfNullOrEmptyString(usersManagementRequest.getPhone()) || Utils.validateIfNullOrEmptyString(usersManagementRequest.getEmail())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Phone/Email both can't be null/empty in request ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			usersManagementResponse = validateIfUserAlreadyExists(usersManagementRequest , usersManagementResponse, connection);
			if (!ResponseCodes.SUCCESS.equalsIgnoreCase(usersManagementResponse.getResponseCode())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to return without creating user as user already exists for requested content ...": null);
				return usersManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to create user for username: ": null);
			AbstractUserManagementServicesDao.getInstance().createUser(usersManagementRequest, connection);
			usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
			return usersManagementResponse;
		} catch (Exception ex) {
			logger.warn("##Exception## while creating user ...");
			throw new BaseException(ex);
		}
	}
	
	private UsersManagementResponse validateIfUserAlreadyExists(UsersManagementRequest usersManagementRequest, UsersManagementResponse usersManagementResponse, Connection connection) throws BaseException {
		List<User> usersList = null;
		try {
			usersList = new ArrayList<>();
			usersList = AbstractUserManagementServicesDao.getInstance().getUserIfUserAlreadyExists(usersManagementRequest.getUsername(), usersManagementRequest.getPhone(), usersManagementRequest.getEmail(), connection);
			if (!Utils.isNullOrEmptyCollection(usersList)) {
				if (usersManagementRequest.getUsername().equalsIgnoreCase(usersList.get(0).getUsername())) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested username ..: ": null);
					usersManagementResponse.setResponseCode(ResponseCodes.USERNAME_ALREADY_EXISTS);
					usersManagementResponse.setResponseDesc(ResponseCodes.USERNAME_ALREADY_EXISTS_DESCRIPTION);
					return usersManagementResponse;
				}
				if (usersManagementRequest.getEmail().equalsIgnoreCase(usersList.get(0).getEmail())) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested email ..: ": null);
					usersManagementResponse.setResponseCode(ResponseCodes.EMAIL_ALREADY_EXISTS);
					usersManagementResponse.setResponseDesc(ResponseCodes.EMAIL_ALREADY_EXISTS_DESCRIPTION);
					return usersManagementResponse;
				}
				if (usersManagementRequest.getPhone().equalsIgnoreCase(usersList.get(0).getPhoneNumber())) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested phone number ..: ": null);
					usersManagementResponse.setResponseCode(ResponseCodes.PHONE_ALREADY_EXISTS);
					usersManagementResponse.setResponseDesc(ResponseCodes.PHONE_ALREADY_EXISTS_DESCRIPTION);
					return usersManagementResponse;
				}
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested content ..: ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			} else {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User doesn't exist against requested content ..: ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
				usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
				return usersManagementResponse;
			}
		}  catch (Exception ex) {
			logger.warn("##Exception## while validating if user already exists ...");
			throw new BaseException(ex);
		}
	}
}
