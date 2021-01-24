package database.manager.methods;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import common.exception.handling.BaseException;
import database.manager.beans.DatabaseInfo;

public abstract class AbstractCommonDbMethods {
	public static final String FETCH_DATABASE = "select id, db_code, name, url, user, password from databases_info where db_code = ?";
	
	AbstractCommonDbMethods() {
	}
	public static AbstractCommonDbMethods getInstance()  throws BaseException {
		return new CommonDaoMethods();
	}
	public abstract List<Map<Integer, Object>> select(String query, List<Object> parameters, Connection con)  throws BaseException;
	public abstract Integer update(String query, List<Object> parameters, Connection con)  throws BaseException;
	public abstract Integer updateWithKeyReturn(String query, List<Object> parameters, Connection con)  throws BaseException;
	public abstract DatabaseInfo getDatabaseInfo(String dbCode, Connection connection)  throws BaseException;
	public abstract int executeBatch(List<String> queries, List<Object> parameters, Connection con)  throws BaseException;
}
