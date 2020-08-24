package user.management.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.bl.AbstractUserManagementServicesHandler;

public class UserManagementService {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest, Connection con) {
		UsersManagementResponse usersManagementResponse = null;
		if (null == usersManagementRequest) {
			return usersManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? "Going to call userManagementService Service": null);
		usersManagementResponse = AbstractUserManagementServicesHandler.getInstance(usersManagementRequest.getUserManagementServiceAction()).userManagementService(usersManagementRequest, con);
		return usersManagementResponse;
	}
	
	
}
