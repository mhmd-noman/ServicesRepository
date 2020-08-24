package user.management.services.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.dao.AbstractUserManagementServicesDao;

public class UpdateUser extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection) {
		UsersManagementResponse usersManagementResponse = null;
		
		usersManagementResponse = new UsersManagementResponse();
		if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
			logger.info(logger.isInfoEnabled() ? "Going to update user for user id: ": null);
		}
		
		AbstractUserManagementServicesDao.getInstance().updateUser(usersManagementRequest, connection);
		usersManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		usersManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return usersManagementResponse;
	}
}
