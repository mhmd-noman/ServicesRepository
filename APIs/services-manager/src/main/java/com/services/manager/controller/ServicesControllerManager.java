package com.services.manager.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.services.configurator.ServicesConfigurator;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

@RestController
public class ServicesControllerManager {
	private static final Logger logger = LoggerFactory.getLogger(ServicesControllerManager.class);

	@GetMapping(path = "iteam/services")
	public String welcomeToEServices() throws BaseException {
		return "Welcome to <b>E-Services</b> - Powered by iTeam.<br>\nDeveloped by: <b>Muhammad Noman</b><br>\niTeam provides E-Services to support E Commerce Systems.";
	}

	@PostMapping(path = "iteam/services/{dbCode}/getUsers")
	public MainResponseObject getUsers(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException, SQLException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call getUsers Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.getUsers(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling getUsers Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling getUsers Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/createUser")
	public MainResponseObject createUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call createUser Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.createUser(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling createUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling createUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/updateUser")
	public MainResponseObject updateUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		try {
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call updateUser Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.updateUser(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling updateUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling updateUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/deleteUser")
	public MainResponseObject deleteUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call deleteUser Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.deleteUser(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling deleteUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling deleteUser Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	} 

	@PostMapping("iteam/services/{dbCode}/addProduct")
	public MainResponseObject addProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call addProduct Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.addProduct(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling addProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling addProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/updateProduct")
	public MainResponseObject updateProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call updateProduct Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.updateProduct(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling updateProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling updateProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/deleteProduct")
	public MainResponseObject deleteProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call deleteProduct Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.deleteProduct(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling deleteProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling deleteProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/getProducts")
	public MainResponseObject getProducts(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call getProducts Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.getProducts(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling getProducts Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling getProducts Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/placeOrder")
	public MainResponseObject placeOrder(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call placeOrder Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.placeOrder(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling placeOrder Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling placeOrder Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/cancelOrder")
	public MainResponseObject cancelOrder(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException, SQLException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call cancelOrder Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.cancelOrder(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling cancelOrder Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling cancelOrder Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/getOrders")
	public MainResponseObject getOrders(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call getOrders Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.getOrders(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling getOrders Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling getOrders Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/contactUs")
	public MainResponseObject contactUs(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException, SQLException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call contactUs Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.contactUs(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while contactUs addProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while contactUs addProduct Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/getQueries")
	public MainResponseObject getQueries(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call getQueries Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.getQueries(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling getQueries Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling getQueries Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/pushWishlist")
	public MainResponseObject pushWishlist(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call pushWishlist Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.pushWishlist(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling pushWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling pushWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/popWishlist")
	public MainResponseObject popWishlist(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call popWishlist Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.popWishlist(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling popWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling popWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}

	@PostMapping("iteam/services/{dbCode}/getWishlist")
	public MainResponseObject getWishlist(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		try {
			ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
			mainRequestObject.setDbCode(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to call getWishlist Service with Client[" +dbCode+ "]": null);
			mainResponseObject = servicesConfigurator.getWishlist(mainRequestObject);
		} catch (SQLException e) {
			logger.info(logger.isInfoEnabled() ? "#SQLException# while calling getWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		} catch (Exception e) {
			logger.info(logger.isInfoEnabled() ? "#Exception# while calling getWishlist Service with Client[" +dbCode+ "]: "+ e: null);
			throw new BaseException(e);
		}
		return mainResponseObject;
	}
}
