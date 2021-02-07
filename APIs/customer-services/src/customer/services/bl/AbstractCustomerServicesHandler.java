package customer.services.bl;

import java.sql.Connection;

import common.enums.CustomerServicesAction;
import common.exception.handling.BaseException;
import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;

public abstract class AbstractCustomerServicesHandler {
	public static AbstractCustomerServicesHandler getInstance() throws BaseException {
		return new GetQueries();
	}

	public static AbstractCustomerServicesHandler getInstance(CustomerServicesAction customerServicesAction) throws BaseException {
		AbstractCustomerServicesHandler abstractCustomerServicesHandler = null;
		switch(customerServicesAction) {
		case CONTACT_US:
			abstractCustomerServicesHandler = new ContactUs();
			break;
		case GET_QUERIES:
			abstractCustomerServicesHandler = new GetQueries();
			break;
		case PUSH_WISHLIST:
			abstractCustomerServicesHandler = new PushWishList();
			break;
		case POP_WISHLIST:
			abstractCustomerServicesHandler = new PopWishList();
			break;
		case GET_WISHLIST:
			abstractCustomerServicesHandler = new GetWishList();
			break;
		default:
			abstractCustomerServicesHandler = new GetQueries();
			break;
		}
		return abstractCustomerServicesHandler;
	}

	public abstract CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection) throws BaseException;
}