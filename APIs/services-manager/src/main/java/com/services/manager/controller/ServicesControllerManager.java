package com.services.manager.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.services.configurator.ServicesConfigurator;

import common.request.MainRequestObject;
import common.response.MainResponseObject;

@RestController
public class ServicesControllerManager {

	@PostMapping(path = "{dbCode}/getUsers")
	public MainResponseObject getUsers(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.getUsers(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("{dbCode}/createUser")
	public MainResponseObject createUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.createUser(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("{dbCode}/updateUser")
	public MainResponseObject updateUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.updateUser(mainRequestObject);
		return mainResponseObject;
	}
	
	@PostMapping("{dbCode}/deleteUser")
	public MainResponseObject deleteUser(@PathVariable("dbCode") String dbCode, @RequestBody MainRequestObject mainRequestObject) {
		MainResponseObject mainResponseObject = null;
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.deleteUser(mainRequestObject);
		return mainResponseObject;
	}
}
