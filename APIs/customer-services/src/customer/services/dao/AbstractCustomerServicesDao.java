package customer.services.dao;

import java.sql.Connection;
import java.util.List;

import common.beans.Query;
import customer.services.beans.CustomerServicesRequest;

public abstract class AbstractCustomerServicesDao {
	public static final String CONTACT_US    = "insert into contact_us (name, email, phone, message) VALUES (?, ?, ?, ?)";
	public static final String GET_QUERIES   = "select id, name, email, phone, message from contact_us where name is not null ";
	public static final String ID            = "and id = ? ";
	public static final String USERNAME      = "and name = ? ";
	public static final String EMAIL         = "and email = ? ";
	public static final String PHONE         = "and phone = ? ";
	
	public static AbstractCustomerServicesDao getInstance() {
		return new CustomerServicesDaoImpl();
	}
	public abstract void contactUs(CustomerServicesRequest customerServicesRequest, Connection connection);
	public abstract List<Query> getQueries(CustomerServicesRequest customerServicesRequest, Connection connection);
}
