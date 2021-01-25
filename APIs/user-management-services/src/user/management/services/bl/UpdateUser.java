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

public class UpdateUser extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection) throws BaseException {
		UsersManagementResponse usersManagementResponse = null;
		try {
			usersManagementResponse = new UsersManagementResponse();
			if (null == usersManagementRequest) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for updateUser ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			if (Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Username can't be null/empty in request of updateUser ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc("Username cannot be null/empty.");
				return usersManagementResponse;
			}
			validateIfUpdationContentAlreadyExists(usersManagementRequest, usersManagementResponse, connection);
			if (!ResponseCodes.SUCCESS.equalsIgnoreCase(usersManagementResponse.getResponseCode())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to return without creating user as user already exists for requested content ...": null);
				return usersManagementResponse;
			}
			AbstractUserManagementServicesDao.getInstance().updateUser(usersManagementRequest, connection);
			usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while updating user ...");
			throw new BaseException(ex);
		}
		return usersManagementResponse;
	}
	
	private UsersManagementResponse validateIfUpdationContentAlreadyExists(UsersManagementRequest usersManagementRequest, UsersManagementResponse usersManagementResponse, Connection connection) throws BaseException {
		List<User> usersList = null;
		boolean ifUserExistsForUpdate = false;
		try {
			usersList = new ArrayList<>();
			usersList = AbstractUserManagementServicesDao.getInstance().getUserIfUserAlreadyExists(usersManagementRequest.getUsername(), usersManagementRequest.getPhone(), usersManagementRequest.getEmail(), connection);
			if (!Utils.isNullOrEmptyCollection(usersList)) {
				for (User user : usersList) {
					if (usersManagementRequest.getUsername().equalsIgnoreCase(user.getUsername())) {
						logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested username ... ": null);
						ifUserExistsForUpdate = true;
					}
					if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getEmail()) && usersManagementRequest.getEmail().equalsIgnoreCase(user.getEmail())) {
						logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested email ... ": null);
						usersManagementResponse.setResponseCode(ResponseCodes.EMAIL_ALREADY_EXISTS);
						usersManagementResponse.setResponseDesc(ResponseCodes.EMAIL_ALREADY_EXISTS_DESCRIPTION);
						return usersManagementResponse;
					}
					if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getPhone()) && usersManagementRequest.getPhone().equalsIgnoreCase(user.getPhoneNumber())) {
						logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User already exists against requested phone number ... ": null);
						usersManagementResponse.setResponseCode(ResponseCodes.PHONE_ALREADY_EXISTS);
						usersManagementResponse.setResponseDesc(ResponseCodes.PHONE_ALREADY_EXISTS_DESCRIPTION);
						return usersManagementResponse;
					}
				}
				if (!ifUserExistsForUpdate) {
					logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User doesn't exist at all ... ": null);
					usersManagementResponse.setResponseCode(ResponseCodes.USER_NOT_EXISTS);
					usersManagementResponse.setResponseDesc(ResponseCodes.USER_NOT_EXISTS_DESCRIPTION);
					return usersManagementResponse;
				}
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User can be proceeded for update request ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
				usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
				return usersManagementResponse;
			} else {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "User doesn't exist at all ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.USER_NOT_EXISTS);
				usersManagementResponse.setResponseDesc(ResponseCodes.USER_NOT_EXISTS_DESCRIPTION);
				return usersManagementResponse;
			}
		}  catch (Exception ex) {
			logger.warn("##Exception## while validating if user updation content already exists ...");
			throw new BaseException(ex);
		}
	}
}
