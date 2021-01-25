package com.services.get.users.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import user.management.services.UserManagementService;
import user.management.services.beans.UsersManagementRequest;
import user.management.services.beans.UsersManagementResponse;
import user.management.services.utils.UserManagementServiceAction;

public class GetUsersConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(GetUsersConfigurator.class);

	public MainResponseObject getUsers(MainRequestObject mainRequestObject, Connection con) throws BaseException {
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
		logger.info(logger.isInfoEnabled() ? "Requested Content for getUsers: [" +usersManagementRequest+ "]": null);
		if (null != mainRequestObject) {
			usersManagementRequest.setEmail(mainRequestObject.getEmail());
			usersManagementRequest.setFirstName(mainRequestObject.getFirstName());
			usersManagementRequest.setLastName(mainRequestObject.getLastName());
			usersManagementRequest.setPassword(mainRequestObject.getPassword());
			usersManagementRequest.setPhone(mainRequestObject.getPhoneNumber());
			usersManagementRequest.setUsername(mainRequestObject.getUsername());
			usersManagementRequest.setUserManagementServiceAction(UserManagementServiceAction.GET_USERS);
			mainRequestObject.setServiceId(UserManagementServiceAction.GET_USERS.toString());
		}
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, UsersManagementResponse usersManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response Recieved for getUsers: [" +usersManagementResponse+ "]": null);
		if (null != usersManagementResponse) {
			mainResponseObject.setResponseCode(usersManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(usersManagementResponse.getResponseDesc());
			mainResponseObject.setUsers(usersManagementResponse.getUsers());
		}
	}
}
