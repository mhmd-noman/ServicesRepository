package transaction.logging.service.dao;

import java.sql.Connection;

import transaction.logging.service.beans.TransactionLoggingRequest;

public abstract class AbstractTransactionLoggingServiceDao {
	public static final String CREATE_USER = "insert into users (email, enabled, first_name, last_name, password, phone, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_USER = "update users set email = ?, enabled = ?, first_name = ?, last_name = ?, password = ?, phone = ? where username = ?";
	public static final String DELETE_USER = "delete users where username = ?";
	public static final String GET_USERS   = "select id, email, enabled, first_name, last_name, password, phone, username from users where enabled = true ";
	public static final String USERNAME    = "and username = ? ";
	public static final String FIRST_NAME  = "and first_name = ? ";
	public static final String LAST_NAME   = "and last_name = ? ";
	
	public static AbstractTransactionLoggingServiceDao getInstance() {
		return new TransactionLoggingServiceDaoImpl();
	}
	public abstract void logTransaction(TransactionLoggingRequest usersManagementRequest, Connection connection);
}
