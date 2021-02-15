package invoice.generation.service.dao;

import java.sql.Connection;

import common.exception.handling.BaseException;
import invoice.generation.service.beans.InvoiceGenerationRequest;

public abstract class AbstractInvoiceGenerationServiceDao {
	public static final String CREATE_USER    = "insert into users (email, enabled, first_name, last_name, password, phone, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_USER    = "update users set email = ?, enabled = ?, first_name = ?, last_name = ?, password = ?, phone = ? where username = ?";
	public static final String DELETE_USER    = "delete from users where username = ?";
	public static final String GET_USERS      = "select id, email, enabled, first_name, last_name, password, phone, username from users where enabled = true ";
	public static final String EMAIL          = "and email = ? ";
	public static final String OR_EMAIL       = "or email = ? ";
	public static final String USERNAME       = "and username = ? ";
	public static final String PHONE_NUMBER   = "and phone = ? ";
	public static final String OR_PHONE_NUMBER= "or phone = ? ";
	public static final String FIRST_NAME     = "and first_name = ? ";
	public static final String LAST_NAME      = "and last_name = ? ";
	public static final String VALIDATE_IF_USER_ALREADY_EXISTS = "select id, email, enabled, first_name, last_name, password, phone, username from users where username = ? ";
	
	public static AbstractInvoiceGenerationServiceDao getInstance() {
		return new InvoiceGenerationServiceDaoImpl();
	}
	public abstract void getInvoiceConfigs(InvoiceGenerationRequest invoiceGenerationRequest, Connection connection) throws BaseException;
}
