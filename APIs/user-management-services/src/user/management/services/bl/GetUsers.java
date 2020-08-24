package user.management.services.bl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.beans.User;
import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.dao.AbstractUserManagementServicesDao;

public class GetUsers extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection) {
		UsersManagementResponse usersManagementResponse = null;
		List<User> usersList = null;

		usersManagementResponse = new UsersManagementResponse();
		usersList = new ArrayList<>();
		if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
			usersManagementResponse = new UsersManagementResponse();
			logger.info(logger.isInfoEnabled() ? "Going to create user for username: ": null);
		}
		usersList = AbstractUserManagementServicesDao.getInstance().getUsers(usersManagementRequest, connection);
		if (!Utils.isNullOrEmptyCollection(usersList)) {
			logger.info(logger.isInfoEnabled() ? "Retrieved Users: "+ usersList: null);
			usersManagementResponse.setUsers(usersList);
		}
		usersManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		usersManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return usersManagementResponse;
	}
}
