/**
* @author  Muhammad Noman
* @version 1.0
* @since   2020-December-27
*/
package transaction.logging.service;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;
import transaction.logging.service.bl.AbstractTransactionLoggingServiceHandler;

public class TransactionLoggingService {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingService.class);
	
	public TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest transLoggingManagementRequest, Connection con) throws BaseException {
		TransactionLoggingResponse transLoggingManagementResponse = null;
		try {
			if (null == transLoggingManagementRequest) {
				return transLoggingManagementResponse;
			}
			logger.info(logger.isInfoEnabled() ? "Going to call transactionLogging Service": null);
			transLoggingManagementResponse = AbstractTransactionLoggingServiceHandler.getInstance().transLoggingManagementService(transLoggingManagementRequest, con);
			return transLoggingManagementResponse;
		} catch (Exception ex) {
			logger.warn("##Exception## in transLoggingService ...");
			throw new BaseException(ex);
		}
	}
}
