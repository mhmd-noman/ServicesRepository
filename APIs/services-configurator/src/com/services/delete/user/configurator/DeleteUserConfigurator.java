package com.services.delete.user.configurator;

import java.sql.Connection;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.utils.UserManagementServiceAction;

public class DeleteUserConfigurator {
	public MainResponseObject deleteUser(MainRequestObject mainRequestObject, Connection con) {
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
			usersManagementRequest.setPhone(mainRequestObject.getUserInfo().getPhoneNumber());
			usersManagementRequest.setUsername(mainRequestObject.getUserInfo().getUsername());
			usersManagementRequest.setUserManagementServiceAction(UserManagementServiceAction.DELETE_USER);
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
