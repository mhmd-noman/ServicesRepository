package common.utilities.methods;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.beans.Order;
import common.utilities.constants.CommonConstants;

public class Utils {
	public static boolean isNullOrEmptyCollection(final Collection<?> c) {
	    return c == null || c.isEmpty();
	}
	
	public static boolean isNullOrEmptyMap(final Map<?,?> c) {
	    return c == null || c.isEmpty();
	}
	
	public static boolean isNullOrEmptyString(String s) {
	    return s == null ||s.isEmpty() || s.trim().length() < 1;
	}
	
	public static boolean validateIfNullOrEmptyString(String str) {
		if (null == str || str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidString(String str) {
		if (null != str && " " != str && str.trim().length() > 1) {
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
	
	public static boolean validateIfNullOrInvalidDouble(String number) {
		try {
			if (null == number) {
				return true;
			}
			Double.parseDouble(number);
		} catch (NumberFormatException ex) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidDate(Date date, String formatForValidation) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatForValidation);
		String dateToBeValidated = null; 
		boolean valid = false;
		
		if (null == date) {
			return false;
		}
		
		dateToBeValidated = date.toString();
		if (isNullOrEmptyString(dateToBeValidated)) {
			return false;
		}
		
		try {
			sdf.parse(dateToBeValidated);
			// strict mode - check 30 or 31 days, leap year
			sdf.setLenient(false);
			valid = true;
		} catch (ParseException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
	
	public static boolean isValidDateForString(String date, String formatForValidation) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatForValidation);
		boolean valid = false;
		
		if (isNullOrEmptyString(date)) {
			return false;
		}
		try {
			sdf.parse(date);
			// strict mode - check 30 or 31 days, leap year
			sdf.setLenient(false);
			valid = true;
		} catch (ParseException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
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
	
	public static String [] convertCommaSeparatedStringToStringArray(String commaSeperatedString) {
		if (null == commaSeperatedString || commaSeperatedString.trim().length() < 1) {
			return null;
		}
		return commaSeperatedString.split(",");
	}
	
	public static Integer [] convertCommaSeparatedStringToIntegerArray(String commaSeperatedString) {
		String [] array = null;
		Integer[] intArray = null;
		
		if (null == commaSeperatedString || commaSeperatedString.trim().length() < 1) {
			return null;
		}
		array = commaSeperatedString.split(",");
		intArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			intArray[i] = Integer.parseInt(array[i]);
		}
		return intArray;
	}
	
	public static List<Integer> convertCommaSeparatedStringToIntegerList(String commaSeperatedString) {
		String [] array = null;
		 List<Integer> intList = null;
		
		if (null == commaSeperatedString || commaSeperatedString.trim().length() < 1) {
			return null;
		}
		array = commaSeperatedString.split(",");
		intList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			intList.add(Integer.parseInt(array[i]));
		}
		return intList;
	}
	
	public static Date getCurrentDateTime() {
		return new Date(System.currentTimeMillis());
	}
	
	public static String getCurrentDateTimeInString() {
		return new Date(System.currentTimeMillis()).toString();
	}
	
	public static Double getWeightInKilograms(String weightInPounds) {
		if (!validateIfNullOrInvalidDouble(weightInPounds)) {
			return Double.parseDouble(weightInPounds) * Double.parseDouble(CommonConstants.POUNDS_TO_KILOGRAM);
		}
		return 0.0d;
	}
}
