package api.communicator.methods;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public abstract class AbstractApiCommunicationMethods {
	public static final String FETCH_DATABASE = "select id, db_code, name, url, user, password from databases_info where db_code = ?";
	
	AbstractApiCommunicationMethods() {
	}
	public static AbstractApiCommunicationMethods getInstance()  throws BaseException {
		return new ApiCommunicationMethods();
	}
	public abstract MainResponseObject postRequest(MainRequestObject request, String url) throws Exception;
}
