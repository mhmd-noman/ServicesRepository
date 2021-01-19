package customer.services.bl;

import java.sql.Connection;

import customer.services.beans.CustomerServicesRequest;
import customer.services.beans.CustomerServicesResponse;
import customer.services.utils.CustomerServicesAction;

public abstract class AbstractCustomerServicesHandler {
	public static AbstractCustomerServicesHandler getInstance() {
		return new GetQueries();
	}

	public static AbstractCustomerServicesHandler getInstance(CustomerServicesAction customerServicesAction) {
		AbstractCustomerServicesHandler abstractCustomerServicesHandler = null;
		switch(customerServicesAction) {
		case CONTACT_US:
			abstractCustomerServicesHandler = new ContactUs();
			break;
		case GET_QUERIES:
			abstractCustomerServicesHandler = new GetQueries();
			break;
		default:
			abstractCustomerServicesHandler = new GetQueries();
			break;
		}
		return abstractCustomerServicesHandler;
	}

	public abstract CustomerServicesResponse customerServices(CustomerServicesRequest customerServicesRequest,  Connection connection);
}