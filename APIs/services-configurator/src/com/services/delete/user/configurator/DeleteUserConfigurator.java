package com.services.delete.user.configurator;

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

public class DeleteUserConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(DeleteUserConfigurator.class);

	public MainResponseObject deleteUser(MainRequestObject mainRequestObject, Connection con) throws BaseException {
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
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for deleteUser: [" +mainRequestObject+ "]": null);
			usersManagementRequest.setEmail(mainRequestObject.getUserInfo().getEmail());
			usersManagementRequest.setPhone(mainRequestObject.getUserInfo().getPhoneNumber());
			usersManagementRequest.setUsername(mainRequestObject.getUserInfo().getUsername());
			usersManagementRequest.setUserManagementServiceAction(UserManagementServiceAction.DELETE_USER);
			mainRequestObject.setServiceId(UserManagementServiceAction.DELETE_USER.toString());
		}
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, UsersManagementResponse usersManagementResponse) {
		logger.info(logger.isInfoEnabled() ? "Response recieved for deleteUser: [" +usersManagementResponse+ "]": null);
		if (null != usersManagementResponse) {
			mainResponseObject.setResponseCode(usersManagementResponse.getResponseCode());
			mainResponseObject.setResponseDesc(usersManagementResponse.getResponseDesc());
			mainResponseObject.setUsers(usersManagementResponse.getUsers());
		}
	}
}
