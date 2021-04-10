package api.communicator.manager;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.response.MainResponseObject;

public class ApiCommunicationManager {
	private static final Logger logger = LoggerFactory.getLogger(ApiCommunicationManager.class);
	
	public MainResponseObject getConnection(String dbCode) throws ClassNotFoundException, SQLException, BaseException {
		logger.info(logger.isInfoEnabled() ? "Going to get database info for "+dbCode+" from Master database..": null);
		//dbInfo = AbstractApiCommunicationMethods.getInstance().getDatabaseInfo(dbCode, conn);
		return null;
		
	}
}
