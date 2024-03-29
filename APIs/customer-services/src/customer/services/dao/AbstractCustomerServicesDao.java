package customer.services.dao;

import java.sql.Connection;
import java.util.List;

import common.beans.Query;
import common.beans.Wishlist;
import common.exception.handling.BaseException;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;

public abstract class AbstractCustomerServicesDao {
	public static final String CONTACT_US            = "insert into contact_us (name, email, phone, message) VALUES (?, ?, ?, ?)";
	public static final String GET_QUERIES           = "select id, name, email, phone, message from contact_us where name is not null ";
	public static final String INSERT_WISHLIST       = "insert into wishlists (username, wishlist) values (?,?)";
	public static final String PUSH_WISHLIST         = "update wishlists set wishlist = CONCAT(wishlist, ?), last_updated_on = ? where username = ?";
	public static final String UPDATE_WISHLIST       = "update wishlists set wishlist = ?, last_updated_on = ? where username = ?";
	public static final String GET_WISHLIST          = "select username, wishlist from wishlists where username = ? ";
	public static final String VERIFY_IF_USER_EXISTS = "select username from users where username = ? ";
	public static final String ID                    = "and id = ? ";
	public static final String USERNAME              = "and name = ? ";
	public static final String EMAIL                 = "and email = ? ";
	public static final String PHONE                 = "and phone = ? ";
	
	public static AbstractCustomerServicesDao getInstance() throws BaseException {
		return new CustomerServicesDaoImpl();
	}
	public abstract void contactUs(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException;
	public abstract List<Query> getQueries(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException;
	public abstract List<Wishlist> getWishlist(CustomerServicesRequest customerServicesRequest, Connection connection) throws BaseException;
	public abstract void pushWishlist(CustomerServicesRequest customerServicesRequest, CustomerServicesResponse customerServicesResponse, Connection connection) throws BaseException;
	public abstract void popWishlist(CustomerServicesRequest customerServicesRequest, CustomerServicesResponse customerServicesResponse, Connection connection) throws BaseException;
	public abstract boolean ifUserExists(String username, Connection connection) throws BaseException;
}
