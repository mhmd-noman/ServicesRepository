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

public class DeleteUser extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest, Connection connection) throws BaseException {
		UsersManagementResponse usersManagementResponse = null;
		try {
			usersManagementResponse = new UsersManagementResponse();
			if (null == usersManagementRequest) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for deleteUser ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			if (Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Username can't be null/empty in request of deleteUser ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc("Username cannot be null/empty.");
				return usersManagementResponse;
			}
			AbstractUserManagementServicesDao.getInstance().deleteUser(usersManagementRequest, connection);
			usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while deleting user  ...");
			throw new BaseException(ex);
		}
		return usersManagementResponse;
	}
}
