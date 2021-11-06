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

public class GetUsers extends AbstractUserManagementServicesHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	public UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection) throws BaseException {
		UsersManagementResponse usersManagementResponse = null;
		List<User> usersList = null;
		try {
			usersManagementResponse = new UsersManagementResponse();
			usersList = new ArrayList<>();
			if (null == usersManagementRequest) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for getUsers ... ": null);
				usersManagementResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				usersManagementResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
				return usersManagementResponse;
			}
			usersList = AbstractUserManagementServicesDao.getInstance().getUsers(usersManagementRequest, connection);
			if (!Utils.isNullOrEmptyCollection(usersList)) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Retrieved Users: "+ usersList: null);
				usersManagementResponse.setUsers(usersList);
			}
			usersManagementResponse.setResponseCode(ResponseCodes.SUCCESS);
			usersManagementResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
		} catch (Exception ex) {
			logger.warn("##Exception## while getting users ..."+ex);
			throw new BaseException(ex);
		}
		return usersManagementResponse;
	}
}
