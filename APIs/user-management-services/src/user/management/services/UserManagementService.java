/**
* @author  Muhammad Noman
* @version 1.0
* @since   2020-August-09
*/
package user.management.services;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.bl.AbstractUserManagementServicesHandler;
import user.management.services.utils.Constants;

public class UserManagementService {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest, Connection con) throws BaseException {
		UsersManagementResponse usersManagementResponse = null;
		try {
			if (null == usersManagementRequest) {
				return usersManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to call userManagementService Service": null);
			usersManagementResponse = AbstractUserManagementServicesHandler.getInstance(usersManagementRequest.getUserManagementServiceAction()).userManagementService(usersManagementRequest, con);
		} catch (Exception ex) {
			logger.warn("##Exception## in user management service ...");
			throw new BaseException(ex);
		}
		return usersManagementResponse;
	}
}
