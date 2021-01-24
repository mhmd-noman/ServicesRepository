package database.manager.handler;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import database.manager.beans.DatabaseInfo;
import database.manager.methods.AbstractCommonDbMethods;
import database.manager.utils.Constants;

public class DatabaseManager {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
	
	public java.sql.Connection getMDBConnection() throws ClassNotFoundException, SQLException {
		java.sql.Connection conn = null;
		// STEP 2: Register JDBC driver
		Class.forName(Constants.JDBC_DRIVER);
		// STEP 3: Open a connection
		logger.info(logger.isInfoEnabled() ? "Connecting to Master database..": null);
		conn = DriverManager.getConnection(Constants.MASTER_DATEBASE_URL, Constants.USER, Constants.PASS);
		return conn;
	}
	
	public java.sql.Connection getConnection(String dbCode) throws ClassNotFoundException, SQLException, BaseException {
		java.sql.Connection conn = null;
		DatabaseInfo dbInfo = null;
		// STEP 2: Register JDBC driver
		Class.forName(Constants.JDBC_DRIVER);
		
		// STEP 3: Open a connection
		logger.info(logger.isInfoEnabled() ? "Going to get Master database connection ...": null);
		conn = getMDBConnection();
		if (Constants.MASTER_DATEBASE.equalsIgnoreCase(dbCode)) {
			logger.info(logger.isInfoEnabled() ? "No Need to further fetch any instance for database connection as this call was supposed to be executed on Master database instance ...": null);
			return conn;
		}
		
		logger.info(logger.isInfoEnabled() ? "Going to get database info for "+dbCode+" from Master database..": null);
		dbInfo = AbstractCommonDbMethods.getInstance().getDatabaseInfo(dbCode, conn);
		logger.info(logger.isInfoEnabled() ? "Going to get connection for "+dbCode+" database..": null);
		if (null == dbInfo) {
			return null;
		}
		conn = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUser(), dbInfo.getPassword());
		return conn;
	}
}
