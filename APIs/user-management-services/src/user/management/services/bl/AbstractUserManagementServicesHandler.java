package user.management.services.bl;

import java.sql.Connection;

import common.exception.handling.BaseException;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.utils.UserManagementServiceAction;

public abstract class AbstractUserManagementServicesHandler {
	public static AbstractUserManagementServicesHandler getInstance() throws BaseException {
		return new GetUsers();
	}

	public static AbstractUserManagementServicesHandler getInstance(UserManagementServiceAction userManagementServiceAction)  throws BaseException {
		AbstractUserManagementServicesHandler abstractManagementUsersHandler = null;
		switch(userManagementServiceAction) {
		case GET_USERS:
			abstractManagementUsersHandler = new GetUsers();
			break;
		case CREATE_USER:
			abstractManagementUsersHandler = new CreateUser();
			break;
		case UPDATE_USER:
			abstractManagementUsersHandler = new UpdateUser();
			break;
		case DELETE_USER:
			abstractManagementUsersHandler = new DeleteUser();
			break;
		default:
			abstractManagementUsersHandler = new GetUsers();
			break;
		}
		return abstractManagementUsersHandler;
	}

	public abstract UsersManagementResponse userManagementService(UsersManagementRequest usersManagementRequest,  Connection connection)  throws BaseException;
}