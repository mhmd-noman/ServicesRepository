package com.services.manager.oauth2.jwt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtils jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/iteam/services/{dbCode}/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@PathVariable("dbCode") String dbCode, @RequestBody JwtRequest authenticationRequest) throws Exception {
		logger.info(logger.isInfoEnabled() ? "Going to authenticate for requested User: [" +authenticationRequest.getUsername()+ "]": null);
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		logger.info(logger.isInfoEnabled() ? "Going to load user with requested username: [" +authenticationRequest.getUsername()+ "]": null);
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		// Following call can be uncommented in future(having skip above call) if somehow we need to maintain admin persons from database ...
		// final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername(), dbCode);
		
		logger.info(logger.isInfoEnabled() ? "User found successfully against username: [" +authenticationRequest.getUsername()+ "]": null);
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}