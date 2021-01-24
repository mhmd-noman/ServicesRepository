package com.services.manager.oauth2.jwt.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.services.manager.controller.ServicesControllerManager;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${spring.security.user.name}")
	private String username;
	@Value("${spring.security.user.password}")
	private String password;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(logger.isInfoEnabled() ? "Going to load user from user service for username:[" +username+ "]": null);
		
		if (this.username.equals(username)) {
			return new User(this.username, passwordEncoder.encode(this.password), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	// These following 2 methods can be used in future if somehow we need to maintain admin persons from database ...
	public UserDetails loadUserByUsername(String username, String dbCode) throws BaseException {
		logger.info(logger.isInfoEnabled() ? "Going to load user from user service for username:[" +username+ "]": null);
		UserDetails userDetails = loadUserForAuthentication(username, dbCode);
		if (null != userDetails) {
			return userDetails;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public UserDetails loadUserForAuthentication(String username, String dbCode) throws BaseException {
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = new MainResponseObject();
		ServicesControllerManager servicesControllerManager = new ServicesControllerManager();
		logger.info(logger.isInfoEnabled() ? "Going to load user from user service for username:[" +username+ "]": null);
		mainResponseObject = servicesControllerManager.getUsers(mainRequestObject.getDbCode(), mainRequestObject);
		if (null == mainResponseObject || Utils.isNullOrEmptyCollection(mainResponseObject.getUsers())) {
			logger.warn("Invalid credentials has been provided in request as no user found!");
			mainResponseObject.setResponseCode(ResponseCodes.INVALID_CREDENTIALS);
			mainResponseObject.setResponseDesc(ResponseCodes.INVALID_CREDENTIALS_DESCRIPTION);
			return null;
		}
		logger.info(logger.isInfoEnabled() ? "User has been found successfully!!!": null);
		return new User(mainResponseObject.getUsers().get(0).getUsername(), passwordEncoder.encode(mainResponseObject.getUsers().get(0).getPassword()), new ArrayList<>());
	}
}
