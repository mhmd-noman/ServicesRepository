package user.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				usersManagementResponse = new UsersManagementResponse();
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
}
