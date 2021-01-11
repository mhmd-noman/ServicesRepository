package transaction.logging.service.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;
import transaction.logging.service.dao.AbstractTransactionLoggingServiceDao;

public class TransactionLoggingImpl extends AbstractTransactionLoggingServiceHandler {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingImpl.class);
	
	public TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest transactionLoggingRequest,  Connection connection) {
		TransactionLoggingResponse transactionLoggingResponse = null;
		transactionLoggingResponse = new TransactionLoggingResponse();
		if (null == transactionLoggingRequest.getMainRequestObject() || null == transactionLoggingRequest.getMainResponseObject()) {
			logger.info(logger.isInfoEnabled() ? "Transaction can't be logged without main request/response object ... ": null);
			transactionLoggingResponse.setResponseCode(CommonConstants.INVALID_TRANS);
			transactionLoggingResponse.setResponseDesc(CommonConstants.INVALID_TRANS_DESCRIPTION);
		}
		transactionLoggingResponse = new TransactionLoggingResponse();
		logger.info(logger.isInfoEnabled() ? "Going to log transaction ... ": null);
		AbstractTransactionLoggingServiceDao.getInstance().logTransaction(transactionLoggingRequest, connection);
		transactionLoggingResponse.setResponseCode(CommonConstants.SUCCESS);
		transactionLoggingResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return transactionLoggingResponse;
	}
}
