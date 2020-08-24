package com.services.create.user.configurator;
import java.sql.Connection;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.utils.UserManagementServiceAction;
public class CreateUserConfigurator {

	public MainResponseObject createUser(MainRequestObject mainRequestObject, Connection con) {
		MainResponseObject mainResponseObject = new MainResponseObject();
		UserManagementService userManagementService = new UserManagementService();
		UsersManagementRequest usersManagementRequest = new UsersManagementRequest();
		UsersManagementResponse usersManagementResponse = new UsersManagementResponse();
		mapRequest(mainRequestObject, usersManagementRequest);
		usersManagementResponse = userManagementService.userManagementService(usersManagementRequest, con);
		mapResponse(mainResponseObject, usersManagementResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, UsersManagementRequest usersManagementRequest) {
		if (null != mainRequestObject && null != mainRequestObject.getUserInfo()) {
			usersManagementRequest.setEmail(mainRequestObject.getUserInfo().getEmail());
			usersManagementRequest.setEnabled(mainRequestObject.getUserInfo().getEnabled());
			usersManagementRequest.setFirstName(mainRequestObject.getUserInfo().getFirstName());
			usersManagementRequest.setLastName(mainRequestObject.getUserInfo().getLastName());
			usersManagementRequest.setPassword(mainRequestObject.getUserInfo().getPassword());
			usersManagementRequest.setPhone(mainRequestObject.getUserInfo().getPhoneNumber());
			usersManagementRequest.setUsername(mainRequestObject.getUserInfo().getUsername());
			usersManagementRequest.setUserManagementServiceAction(UserManagementServiceAction.CREATE_USER);
		}
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, UsersManagementResponse usersManagementResponse) {
		if (null != usersManagementResponse) {
			mainResponseObject.setResponseCode(usersManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(usersManagementResponse.getResponseDesc());
			mainResponseObject.setUsers(usersManagementResponse.getUsers());
		}
	}
}
