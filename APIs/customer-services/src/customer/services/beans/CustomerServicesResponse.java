package customer.services.beans;

import java.util.List;

import common.beans.Query;

public class CustomerServicesResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private List<Query> queries = null;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public List<Query> getQueries() {
		return queries;
	}
	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerServicesResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", queries=");
		builder.append(queries);
		builder.append("]");
		return builder.toString();
	}
}
