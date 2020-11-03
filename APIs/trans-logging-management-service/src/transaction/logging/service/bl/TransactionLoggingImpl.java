package transaction.logging.service.bl;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.constants.CommonConstants;
import common.utilities.methods.Utils;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;
import transaction.logging.service.dao.AbstractTransactionLoggingServiceDao;

public class TransactionLoggingImpl extends AbstractTransactionLoggingServiceHandler {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingImpl.class);
	
	public TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest usersManagementRequest,  Connection connection) {
		TransactionLoggingResponse usersManagementResponse = null;
		usersManagementResponse = new TransactionLoggingResponse();
		if (!Utils.validateIfNullOrEmptyString(usersManagementRequest.getUsername())) {
			usersManagementResponse = new TransactionLoggingResponse();
		}
		logger.info(logger.isInfoEnabled() ? "Going to create user for username: ": null);
		AbstractTransactionLoggingServiceDao.getInstance().logTransaction(usersManagementRequest, connection);
		usersManagementResponse.setResponseCode(CommonConstants.SUCCESS);
		usersManagementResponse.setResponseDesc(CommonConstants.SUCCESS_DESCRIPTION);
		return usersManagementResponse;
	}
}
