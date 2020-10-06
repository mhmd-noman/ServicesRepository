package database.manager.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utilities.methods.Utils;
import database.manager.beans.DatabaseInfo;

public class CommonDaoMethods extends AbstractCommonDbMethods {
	private static final Logger logger = LoggerFactory.getLogger(CommonDaoMethods.class);
	
	@Override
	public List<Map<Integer, Object>> select(String query, List<Object> parameters, Connection con) {
		List<Map<Integer, Object>> resultSet = new ArrayList<>();
		ResultSetMetaData rsmd = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int columnCount = 0;
		try {
			stmt = con.prepareStatement(query);
			stmt = prepareStatementParams(stmt, parameters);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				Map<Integer, Object> result = new ConcurrentHashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					result.put(i, rs.getObject(i));
				}
				resultSet.add(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	@Override
	public Integer update(String query, List<Object> parameters, Connection con) {
		PreparedStatement stmt = null;
		int updatedRows = 0;
		try {
			stmt = con.prepareStatement(query);
			stmt = prepareStatementParams(stmt, parameters);
			updatedRows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}

	@Override
	public DatabaseInfo getDatabaseInfo(String dbCode, Connection connection) {
		List<Object> paramList = null;
		List<Map<Integer, Object>> dbResultSet = null;

		paramList = new ArrayList<>();		
		if (Utils.validateIfNullOrEmptyString(dbCode)) {
			logger.info(logger.isInfoEnabled() ? "DbCode has been provided null/empty!": null);
			return null;
		}
		paramList.add(dbCode);
		logger.info(logger.isInfoEnabled() ? "Going to fetch Database by using query: " +AbstractCommonDbMethods.FETCH_DATABASE+ " with paramters: "+ paramList: null);
		dbResultSet = select(AbstractCommonDbMethods.FETCH_DATABASE, paramList, connection);	
		return prepareDbData(dbResultSet);
	}
	
	private static DatabaseInfo prepareDbData(List<Map<Integer, Object>> dbResultSet) {
		DatabaseInfo dbInfo = null;
		int index = 0;
		if (null != dbResultSet) {
			for (Map<Integer, Object> userRow : dbResultSet) {
				dbInfo = new DatabaseInfo();
				dbInfo.setId(null != userRow.get(++index) ? (Integer)userRow.get(index): null);
				dbInfo.setDbCode(null != userRow.get(++index) ? (String)userRow.get(index): null);
				dbInfo.setName(null != userRow.get(++index) ? (String)userRow.get(index): null);
				dbInfo.setUrl(null != userRow.get(++index) ? (String)userRow.get(index): null);
				dbInfo.setUser(null != userRow.get(++index) ? (String)userRow.get(index): null);
				dbInfo.setPassword(null != userRow.get(++index) ? (String)userRow.get(index): null);
				index = 0;
			}
		}
		return dbInfo;
	}
	
	private static PreparedStatement prepareStatementParams(PreparedStatement stmt, List<Object> parameters) {
		int i = 1;
		try {
			if (Utils.isNullOrEmptyCollection(parameters)) {
				return stmt;
			}
			for (Object param : parameters) {
				stmt.setObject(i, param);
				i++;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return stmt;
	}

}