package com.services.configurator;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.services.add.product.configurator.AddProductConfigurator;
import com.services.create.user.configurator.CreateUserConfigurator;
import com.services.delete.product.configurator.DeleteProductConfigurator;
import com.services.delete.user.configurator.DeleteUserConfigurator;
import com.services.get.order.configurator.GetOrdersConfigurator;
import com.services.get.product.configurator.GetProductsConfigurator;
import com.services.get.users.configurator.GetUsersConfigurator;
import com.services.place.order.configurator.PlaceOrderConfigurator;
import com.services.remove.order.configurator.RemoveOrderConfigurator;
import com.services.update.product.configurator.UpdateProductConfigurator;
import com.services.update.user.configurator.UpdateUserConfigurator;

import common.request.MainRequestObject;
import common.response.MainResponseObject;
import common.utilities.constants.CommonConstants;
import database.manager.handler.DatabaseManager;

public class ServicesConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(ServicesConfigurator.class);
	private Connection getConnection(String dbCode) {
		Connection con = null; 
		DatabaseManager dbManager = null;
		try {
			dbManager = new DatabaseManager();
			logger.info(logger.isInfoEnabled() ? "Going to get connection for database client: [" +dbCode+ "]": null);
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
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		getUsersConfigurator = new GetUsersConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for getUsers Service with requested content: [" +mainRequestObject+ "]": null);
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
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		createUserConfigurator = new CreateUserConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for createUser Service with requested content: [" +mainRequestObject+ "]": null);
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
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		updateUserConfigurator = new UpdateUserConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for updateUser Service with requested content: [" +mainRequestObject+ "]": null);
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
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		deleteUserConfigurator = new DeleteUserConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for deleteUser Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = deleteUserConfigurator.deleteUser(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject getProducts(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		GetProductsConfigurator getProductsConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		getProductsConfigurator = new GetProductsConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for getProducts Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = getProductsConfigurator.getProducts(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject addProduct(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		AddProductConfigurator addProductConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		addProductConfigurator = new AddProductConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for addProduct Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = addProductConfigurator.addProduct(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject updateProduct(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		UpdateProductConfigurator updateProductConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		updateProductConfigurator = new UpdateProductConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for updateProduct Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = updateProductConfigurator.updateProduct(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject deleteProduct(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		DeleteProductConfigurator deleteProductConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		deleteProductConfigurator = new DeleteProductConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for deleteProduct Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = deleteProductConfigurator.deleteProduct(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject getOrders(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		GetOrdersConfigurator getOrdersConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		getOrdersConfigurator = new GetOrdersConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for getOrders Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = getOrdersConfigurator.getOrders(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject placeOrder(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		PlaceOrderConfigurator placeOrderConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		placeOrderConfigurator = new PlaceOrderConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for placeOrder Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = placeOrderConfigurator.placeOrder(mainRequestObject, con);
		return mainResponseObject;
	}
	
	public MainResponseObject removeOrder(MainRequestObject mainRequestObject) {
		Connection con = null;
		MainResponseObject mainResponseObject = null;
		RemoveOrderConfigurator removeOrderConfigurator = null;
		mainResponseObject = new MainResponseObject();
		con = getConnection(mainRequestObject.getDbCode());
		if (null == con) {
			logger.info(logger.isInfoEnabled() ? "Couldn't get connection for Database Client: [" +mainRequestObject.getDbCode()+ "]": null);
			mainResponseObject.setResponseCode(CommonConstants.DB_CONNECTION_FAILED);
			mainResponseObject.setResponseDesc(CommonConstants.DB_CONNECTION_FAILED_DESC);
			return mainResponseObject;
		}
		removeOrderConfigurator = new RemoveOrderConfigurator();
		logger.info(logger.isInfoEnabled() ? "Going to make call for removeOrder Service with requested content: [" +mainRequestObject+ "]": null);
		mainResponseObject = removeOrderConfigurator.removeOrder(mainRequestObject, con);
		return mainResponseObject;
	}
}
