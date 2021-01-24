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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Statement;

import common.exception.handling.BaseException;
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
					if (null != rs.getObject(i)) {
						result.put(i, rs.getObject(i));
					}
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
	public Integer updateWithKeyReturn(String query, List<Object> parameters, Connection con) {
		PreparedStatement stmt = null;
		int key = 0;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt = prepareStatementParams(stmt, parameters);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	@Override
	public int executeBatch(List<String> queries, List<Object> parameters, Connection con) {
		PreparedStatement stmt = null;
		int updatedRows[] = null;
		int mainCount = 0;
		int count = 1;
		try {
			stmt = con.prepareStatement(queries.get(0));
			for (String query : queries) {
				int i = 1;
				count = StringUtils.countMatches(query, "?");
				while (i <= count) { 
					stmt.setObject(i, parameters.get(mainCount));
					mainCount++;
					i++;
				}
				stmt.addBatch();
			}	
			updatedRows = stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRows.length;
	}

	@Override
	public DatabaseInfo getDatabaseInfo(String dbCode, Connection connection) throws BaseException {
		List<Object> paramList = null;
		List<Map<Integer, Object>> dbResultSet = null;
		try {
			paramList = new ArrayList<>();		
			if (Utils.validateIfNullOrEmptyString(dbCode)) {
				logger.info(logger.isInfoEnabled() ? "DbCode has been provided null/empty!": null);
				return null;
			}
			paramList.add(dbCode);
			logger.info(logger.isInfoEnabled() ? "Going to fetch Database by using query: " +AbstractCommonDbMethods.FETCH_DATABASE+ " with paramters: "+ paramList: null);
			dbResultSet = select(AbstractCommonDbMethods.FETCH_DATABASE, paramList, connection);
		} catch (Exception ex) {
			logger.warn("##Exception## while deleting product ...");
			throw new BaseException(ex);
		}
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
