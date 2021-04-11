package api.communicator.manager;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.communicator.methods.AbstractApiCommunicationMethods;
import api.communicator.utils.Constants;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class ApiCommunicationManager {
	private static final Logger logger = LoggerFactory.getLogger(ApiCommunicationManager.class);
	
	public MainResponseObject getConnection(String dbCode) throws ClassNotFoundException, SQLException, BaseException {
		logger.info(logger.isInfoEnabled() ? "Going to get database info for "+dbCode+" from Master database..": null);
		//dbInfo = AbstractApiCommunicationMethods.getInstance().getDatabaseInfo(dbCode, conn);
		return null;
		
	}
	
	public MainResponseObject postRequest(MainRequestObject request, String api) throws Exception {
		String uri = Constants.BASE_URI + api;
		logger.info(logger.isInfoEnabled() ? "Going to post request for api: "+api: null);
		//dbInfo = AbstractApiCommunicationMethods.getInstance().getDatabaseInfo(dbCode, conn);
		return AbstractApiCommunicationMethods.getInstance().postRequest(request, uri);
	}
}
