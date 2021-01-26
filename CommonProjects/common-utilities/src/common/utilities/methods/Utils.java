package common.utilities.methods;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.beans.Order;

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
	
	public static Double getRetailPrice(Double orgPrice, Double discount) {
		if (validateIfNullOrInvalidDouble(orgPrice)) {
			return 0D;
		}
		if (validateIfNullOrInvalidDouble(discount)) {
			return orgPrice;
		}
		return (orgPrice - (orgPrice * (discount / 100)));
	}
	
	public static Double getTotalPrice(Double price, Integer quantity) {
		if (validateIfNullOrInvalidDouble(price)) {
			return 0D;
		}
		if (validateIfNullOrInvalidInteger(quantity)) {
			return price;
		}
		return (price * quantity);
	}
	
	public static Double getTotalOrgAmountFromOrder(List<Order> orders) {
		if (!Utils.isNullOrEmptyCollection(orders)) {
			Double orgAmount = 0.0;
			for (Order order : orders) {
				orgAmount = orgAmount + order.getOrderOrgAmount();
			}
			return orgAmount;
		} else {
			return 0.0;
		}
	}
	
	public static Double getTotalRtlAmountFromOrder(List<Order> orders) {
		if (!Utils.isNullOrEmptyCollection(orders)) {
			Double orgAmount = 0.0;
			for (Order order : orders) {
				orgAmount = orgAmount + order.getOrderRtlAmount();
			}
			return orgAmount;
		} else {
			return 0.0;
		}
	}
	
	public static Double getOverallDiscountFromOrder(List<Order> orders) {
		if (!Utils.isNullOrEmptyCollection(orders)) {
			Double discount = 0.0;
			for (Order order : orders) {
				discount = discount + order.getOrderCalcDiscount();
			}
			discount = discount / orders.size();
			return discount;
		} else {
			return 0.0;
		}
	}
	
	public static String formatDate(Date date, String format) {
	    SimpleDateFormat formatter = new SimpleDateFormat(format);  
	    return formatter.format(date);    
	}
}
