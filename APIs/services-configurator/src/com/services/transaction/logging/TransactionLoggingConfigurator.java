package com.services.transaction.logging;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import transaction.logging.service.TransactionLoggingService;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;

public class TransactionLoggingConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingConfigurator.class);
	
	public MainResponseObject logTransaction(MainRequestObject mainRequestObject, MainResponseObject mainResponseObject, Connection con) throws BaseException {
		TransactionLoggingService transactionLoggingService = new TransactionLoggingService();
		TransactionLoggingRequest transactionLoggingRequest = new TransactionLoggingRequest();
		TransactionLoggingResponse transactionLoggingResponse = new TransactionLoggingResponse();
		mapRequest(mainRequestObject, mainResponseObject, transactionLoggingRequest);
		transactionLoggingResponse = transactionLoggingService.transLoggingManagementService(transactionLoggingRequest, con);
		mapResponse(mainResponseObject, transactionLoggingResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, MainResponseObject mainResponseObject, TransactionLoggingRequest transactionLoggingRequest) {
		logger.info(logger.isInfoEnabled() ? "Going to log transaction with Content: [" +transactionLoggingRequest+ "]": null);
		if (null != mainRequestObject) {
			transactionLoggingRequest.setMainRequestObject(mainRequestObject);
			transactionLoggingRequest.setMainResponseObject(mainResponseObject);
		}
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, TransactionLoggingResponse transactionLoggingResponse) {
		logger.info(logger.isInfoEnabled() ? "Response Recieved for transactionLoggingResponse: [" +transactionLoggingResponse+ "]": null);
		if (null != transactionLoggingResponse) {
			mainResponseObject.setResponseCode(transactionLoggingResponse.getResponseCode());
			mainResponseObject.setResponseDesc(transactionLoggingResponse.getResponseDesc());
		}
	}
}
