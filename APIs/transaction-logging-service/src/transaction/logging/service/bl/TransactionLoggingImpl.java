package transaction.logging.service.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;
import transaction.logging.service.dao.AbstractTransactionLoggingServiceDao;

public class TransactionLoggingImpl extends AbstractTransactionLoggingServiceHandler {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingImpl.class);
	
	public TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest transactionLoggingRequest,  Connection connection) throws BaseException {
		TransactionLoggingResponse transactionLoggingResponse = null;
		try {
			transactionLoggingResponse = new TransactionLoggingResponse();
			if (null == transactionLoggingRequest.getMainRequestObject() || null == transactionLoggingRequest.getMainResponseObject()) {
				logger.info(logger.isInfoEnabled() ? "Transaction can't be logged without main request/response object ... ": null);
				transactionLoggingResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				transactionLoggingResponse.setResponseDesc(ResponseCodes.INVALID_TRANS_DESCRIPTION);
			}
			transactionLoggingResponse = new TransactionLoggingResponse();
			logger.info(logger.isInfoEnabled() ? "Going to log transaction ... ": null);
			AbstractTransactionLoggingServiceDao.getInstance().logTransaction(transactionLoggingRequest, connection);
			transactionLoggingResponse.setResponseCode(transactionLoggingRequest.getMainResponseObject().getResponseCode());
			transactionLoggingResponse.setResponseDesc(transactionLoggingRequest.getMainResponseObject().getResponseDesc());
		} catch (Exception ex) {
			logger.warn("##Exception## while logging transaction ..."+ex);
			throw new BaseException(ex);
		}
		return transactionLoggingResponse;
	}
}
