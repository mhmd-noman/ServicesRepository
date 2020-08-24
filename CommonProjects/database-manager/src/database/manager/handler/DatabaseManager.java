package database.manager.handler;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.manager.beans.DatabaseInfo;
import database.manager.methods.AbstractCommonDbMethods;
import database.manager.utils.Constants;

public class DatabaseManager {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
	
	public java.sql.Connection getMDBConnection()
			throws ClassNotFoundException, SQLException {
		java.sql.Connection conn = null;
		// STEP 2: Register JDBC driver
		Class.forName(Constants.JDBC_DRIVER);
		// STEP 3: Open a connection
		logger.info(logger.isInfoEnabled() ? "Connecting to master database..": null);
		conn = DriverManager.getConnection(Constants.MDB_URL, Constants.USER, Constants.PASS);
		return conn;
	}
	
	public java.sql.Connection getConnection(String dbCode)
			throws ClassNotFoundException, SQLException {
		java.sql.Connection conn = null;
		DatabaseInfo dbInfo = null;
		// STEP 2: Register JDBC driver
		Class.forName(Constants.JDBC_DRIVER);
		// STEP 3: Open a connection
		conn = getMDBConnection();
		logger.info(logger.isInfoEnabled() ? "Going to get database info for "+dbCode+" database..": null);
		dbInfo = AbstractCommonDbMethods.getInstance().getDatabaseInfo(dbCode, conn);
		logger.info(logger.isInfoEnabled() ? "Going to get connection for "+dbCode+" database..": null);
		if (null == dbInfo) {
			return null;
		}
		conn = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUser(), dbInfo.getPassword());
		return conn;
	}
}
