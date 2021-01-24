package transaction.logging.service.dao;

import java.sql.Connection;

import common.exception.handling.BaseException;
import transaction.logging.service.beans.TransactionLoggingRequest;

public abstract class AbstractTransactionLoggingServiceDao {
	public static final String LOG_TRANSACTION = "INSERT INTO transaction_infos (service_id, username, order_id, response_code, response_desc, org_amount, processed_amount, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static AbstractTransactionLoggingServiceDao getInstance() throws BaseException {
		return new TransactionLoggingServiceDaoImpl();
	}
	public abstract void logTransaction(TransactionLoggingRequest usersManagementRequest, Connection connection) throws BaseException;
}
