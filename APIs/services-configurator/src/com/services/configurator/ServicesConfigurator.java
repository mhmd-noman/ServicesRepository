package com.services.configurator;

import java.sql.Connection;
import java.sql.SQLException;

import com.services.create.user.configurator.CreateUserConfigurator;
import com.services.delete.user.configurator.DeleteUserConfigurator;
import com.services.get.users.configurator.GetUsersConfigurator;

import com.services.update.user.configurator.UpdateUserConfigurator;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import common.utilities.constants.CommonConstants;
import database.manager.handler.DatabaseManager;

public class ServicesConfigurator {
	private Connection getConnection(String dbCode) {
		Connection con = null; 
		DatabaseManager dbManager = null;
		try {
			dbManager = new DatabaseManager();
			con = dbManager.getConnection(dbCode);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public MainResponseObject getUsers(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		GetUsersConfigurator getUsersConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		getUsersConfigurator = new GetUsersConfigurator();
		mainResponseObject = getUsersConfigurator.getUsers(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject createUser(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		CreateUserConfigurator createUserConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		createUserConfigurator = new CreateUserConfigurator();
		mainResponseObject = createUserConfigurator.createUser(mainRequestObject, con);
		return mainResponseObject;
	}
	
	
	public MainResponseObject updateUser(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		UpdateUserConfigurator updateUserConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		updateUserConfigurator = new UpdateUserConfigurator();
		mainResponseObject = updateUserConfigurator.updateUser(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject deleteUser(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		DeleteUserConfigurator deleteUserConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		deleteUserConfigurator = new DeleteUserConfigurator();
		mainResponseObject = deleteUserConfigurator.deleteUser(mainRequestObject, con);
		return mainResponseObject;
	}
}
