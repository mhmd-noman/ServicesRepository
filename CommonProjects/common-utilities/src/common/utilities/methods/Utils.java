package common.utilities.methods;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class Utils {
	public static boolean isNullOrEmptyCollection(final Collection< ? > c ) {
	    return c == null || c.isEmpty();
	}
	
	public static boolean validateIfNullOrEmptyString(String str) {
		if (null == str || str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean validateIfNullOrInvalidInteger(Integer number) {
		if (null == number || number < 0) {
			return true;
		}
		return false;
	}
	
	public static boolean validateIfNullOrInvalidDouble(Double number) {
		if (null == number) {
			return true;
		}
		return false;
	}
	
	public static PreparedStatement prepareStatementParams(PreparedStatement stmt, List<Object> parameters) {
		int i = 1;
		try {
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
