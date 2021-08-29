package database.manager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;

public class DatabaseUtilities {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseUtilities.class);
	
	public static void disposeResources(PreparedStatement preparedStatment, ResultSet resultSet) throws BaseException {
		logger.info(logger.isInfoEnabled() ? "Disposing DB resources ...": null);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
			logger.warn("##SQLException## while disposing ResultSet ...");
			throw new BaseException(ex);
		} finally {
			try {
				if (null != preparedStatment) {
					preparedStatment.close();
				}
			} catch (SQLException ex) {
				logger.warn("##SQLException## while disposing PreparedStatement ...");
				throw new BaseException(ex);
			}
		}
	}
	
	public static void disposeResources(Statement statement, ResultSet resultSet) throws BaseException {
		logger.info(logger.isInfoEnabled() ? "Disposing DB resources ...": null);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
			logger.warn("##SQLException## while disposing ResultSet ...");
			throw new BaseException(ex);
		} finally {
			try {
				if (null != statement) {
					statement.close();
				}
			} catch (SQLException ex) {
				logger.warn("##SQLException## while disposing PreparedStatement ...");
				throw new BaseException(ex);
			}
		}
	}
	
	public static void disposeConnection(Connection con) throws BaseException, SQLException {
		logger.info(logger.isInfoEnabled() ? "Disposing DB connection ...": null);
		if (null != con) {
			try {
				if (!con.getAutoCommit()) {
					con.rollback();
					con.setAutoCommit(true);
				}
			} catch (SQLException ex) {
				logger.warn("##SQLException## while disposing connection ...");
				throw new BaseException(ex);
			} finally {
				con.close();
			}
		}
	}
	
	public static Connection commitConnection(Connection con) throws BaseException, SQLException {
		logger.info(logger.isInfoEnabled() ? "Committing DB connection ...": null);
		if (null != con && !con.getAutoCommit()) {
			try {
				con.commit();
				con.setAutoCommit(true);
				return con;
			} catch (SQLException ex) {
				logger.warn("##SQLException## while committing connection ...");
				throw new BaseException(ex);
			}
		}
		return con;
	}
	
	public static Connection rollbackConnection(Connection con) throws BaseException, SQLException {
		logger.info(logger.isInfoEnabled() ? "Rolling back DB connection ...": null);
		if (null != con) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				return con;
			} catch (SQLException ex) {
				logger.warn("##SQLException## while Rolling back connection ...");
				throw new BaseException(ex);
			}
		}
		return con;
	}
	
	public static boolean inTransConnection(Connection con) throws BaseException, SQLException {
		logger.info(logger.isInfoEnabled() ? "Rolling back DB connection ...": null);
		if (null != con) {
			try {
				return (!con.getAutoCommit());
			} catch (SQLException ex) {
				logger.warn("##SQLException## while Rolling back connection ...");
				throw new BaseException(ex);
			}
		}
		return false;
	}
}
