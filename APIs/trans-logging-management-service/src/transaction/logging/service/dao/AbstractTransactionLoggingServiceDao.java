package transaction.logging.service.dao;

import java.sql.Connection;

import transaction.logging.service.beans.TransactionLoggingRequest;

public abstract class AbstractTransactionLoggingServiceDao {
	public static final String LOG_TRANSACTION = "INSERT INTO transaction_infos (service_id, username, order_id, response_code, response_desc, processed_amount, discount, description, trans_time, last_updated_on, trans_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static AbstractTransactionLoggingServiceDao getInstance() {
		return new TransactionLoggingServiceDaoImpl();
	}
	public abstract void logTransaction(TransactionLoggingRequest usersManagementRequest, Connection connection);
}
