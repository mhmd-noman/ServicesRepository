package api.communicator.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.communicator.methods.AbstractApiCommunicationMethods;
import api.communicator.utils.Constants;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class ApiCommunicationManager {
	private static final Logger logger = LoggerFactory.getLogger(ApiCommunicationManager.class);

	public MainResponseObject postRequest(MainRequestObject request, String api) throws Exception {
		String uri = Constants.BASE_URI + Constants.CLIENT_IDENTIFIER + api;
		logger.info(logger.isInfoEnabled() ? "Going to post request for api: "+api: null);
		return AbstractApiCommunicationMethods.getInstance().postRequest(request, uri);
	}
	
	public MainResponseObject postRequest(MainRequestObject request, String clientIdentifier, String api) throws Exception {
		String uri = Constants.BASE_URI + clientIdentifier + "/" + api;
		logger.info(logger.isInfoEnabled() ? "Going to post request for api: "+api: null);
		return AbstractApiCommunicationMethods.getInstance().postRequest(request, uri);
	}
}
