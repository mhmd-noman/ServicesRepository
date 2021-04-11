package api.communicator.methods;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public abstract class AbstractApiCommunicationMethods {
	AbstractApiCommunicationMethods() {
	}
	public static AbstractApiCommunicationMethods getInstance()  throws BaseException {
		return new ApiCommunicationMethods();
	}
	public abstract MainResponseObject postRequest(MainRequestObject request, String uri) throws Exception;
}
