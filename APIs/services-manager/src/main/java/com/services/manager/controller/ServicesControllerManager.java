package com.services.manager.controller;

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
		return "Welcome to E-Services - Powered by iTeam.\nDeveloped by: Muhammad Noman\niTeam provides E-Services to support E Commerce Systems.";
	}
	
	@PostMapping(path = "iteam/services/{dbCode}/getUsers")
	public MainResponseObject getUsers(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call getUsers Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.getUsers(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/createUser")
	public MainResponseObject createUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call createUser Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.createUser(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/updateUser")
	public MainResponseObject updateUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call updateUser Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.updateUser(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/deleteUser")
	public MainResponseObject deleteUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call deleteUser Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.deleteUser(mainRequestObject);
		return mainResponseObject;
	} 
	
	@PostMapping("iteam/services/{dbCode}/addProduct")
	public MainResponseObject addProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call addProduct Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.addProduct(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/updateProduct")
	public MainResponseObject updateProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call updateProduct Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.updateProduct(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/deleteProduct")
	public MainResponseObject deleteProduct(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call deleteProduct Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.deleteProduct(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/getProducts")
	public MainResponseObject getProducts(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call getProducts Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.getProducts(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/placeOrder")
	public MainResponseObject placeOrder(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call placeOrder Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.placeOrder(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/cancelOrder")
	public MainResponseObject cancelOrder(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call cancelOrder Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.removeOrder(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/getOrders")
	public MainResponseObject getOrders(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call getOrders Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.getOrders(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/contactUs")
	public MainResponseObject contactUs(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call contactUs Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.contactUs(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("iteam/services/{dbCode}/getQueries")
	public MainResponseObject getQueries(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) throws BaseException {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to call getQueries Service with Client[" +dbCode+ "]": null);
		mainResponseObject = servicesConfigurator.getQueries(mainRequestObject);
		return mainResponseObject;
	}
}
