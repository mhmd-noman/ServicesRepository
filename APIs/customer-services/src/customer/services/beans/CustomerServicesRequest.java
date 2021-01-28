package customer.services.beans;

import common.beans.Query;
import common.enums.CustomerServicesAction;

public class CustomerServicesRequest {
	private Query query = null;
	private CustomerServicesAction customerServicesAction = null;
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public CustomerServicesAction getCustomerServicesAction() {
		return customerServicesAction;
	}
	public void setCustomerServicesAction(CustomerServicesAction customerServicesAction) {
		this.customerServicesAction = customerServicesAction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerServicesRequest [query=");
		builder.append(query);
		builder.append(", customerServicesAction=");
		builder.append(customerServicesAction);
		builder.append("]");
		return builder.toString();
	}
}
