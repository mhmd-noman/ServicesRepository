/**
* @author  Muhammad Noman
* @version 1.0
* @since   2020-October-15
*/
package transaction.logging.service;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;
import transaction.logging.service.bl.AbstractTransactionLoggingServiceHandler;

public class TransactionLoggingService {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingService.class);
	
	public TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest transLoggingManagementRequest, Connection con) {
		TransactionLoggingResponse transLoggingManagementResponse = null;
		if (null == transLoggingManagementRequest) {
			return transLoggingManagementResponse;
		}
		logger.info(logger.isInfoEnabled() ? "Going to call userManagementService Service": null);
		transLoggingManagementResponse = AbstractTransactionLoggingServiceHandler.getInstance().transLoggingManagementService(transLoggingManagementRequest, con);
		return transLoggingManagementResponse;
	}
	
	
}
