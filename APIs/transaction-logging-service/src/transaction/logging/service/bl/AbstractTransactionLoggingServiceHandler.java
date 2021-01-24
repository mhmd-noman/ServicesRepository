package transaction.logging.service.bl;

import java.sql.Connection;

import common.exception.handling.BaseException;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.beans.TransactionLoggingResponse;

public abstract class AbstractTransactionLoggingServiceHandler {
	public static AbstractTransactionLoggingServiceHandler getInstance() {
		return new TransactionLoggingImpl();
	}
	public abstract TransactionLoggingResponse transLoggingManagementService(TransactionLoggingRequest transLoggingManagementRequest,  Connection connection) throws BaseException;
}