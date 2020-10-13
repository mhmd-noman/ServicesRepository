package common.utilities.methods;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {
	public static boolean isNullOrEmptyCollection(final Collection<?> c ) {
	    return c == null || c.isEmpty();
	}
	
	public static boolean isNullOrEmptyMap(final Map<?,?> c ) {
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
	
	public static String prepareInClauseString(List<?> values) {
		StringBuilder csv = new StringBuilder();
		String quote = "'";
		for (Object value : values) {
			csv.append(quote + value + quote + ",");
		}
		if (null != csv && !validateIfNullOrEmptyString(csv.toString())) {
			csv.deleteCharAt(csv.length() - 1);
		}
		return csv.toString();
	}
}
