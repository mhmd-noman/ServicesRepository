package com.services.get.users.configurator;

import java.sql.Connection;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.utils.UserManagementServiceAction;

public class GetUsersConfigurator {
	public MainResponseObject getUsers(MainRequestObject mainRequestObject, Connection con) {
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
		if (null != mainRequestObject) {
			usersManagementRequest.setEmail(mainRequestObject.getEmail());
			usersManagementRequest.setEnabled(mainRequestObject.getEnabled());
			usersManagementRequest.setFirstName(mainRequestObject.getFirstName());
			usersManagementRequest.setLastName(mainRequestObject.getLastName());
			usersManagementRequest.setPassword(mainRequestObject.getPassword());
			usersManagementRequest.setPhone(mainRequestObject.getPhoneNumber());
			usersManagementRequest.setUsername(mainRequestObject.getUsername());
			usersManagementRequest.setUserManagementServiceAction(UserManagementServiceAction.GET_USERS);
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
