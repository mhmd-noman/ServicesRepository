package common.request;

import common.beans.User;
import common.enums.UserManagementServiceAction;

public class MainRequestObject {
	private Integer serialNumber = 0;
	private String firstName = null;
	private String lastName = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String phoneNumber = null;
	private String dbCode = null;
	private User userInfo = null;
	String enabled = null;
	private UserManagementServiceAction userManagementServiceAction = null;

	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public UserManagementServiceAction getUserManagementServiceAction() {
		return userManagementServiceAction;
	}
	public void setUserManagementServiceAction(UserManagementServiceAction userManagementServiceAction) {
		this.userManagementServiceAction = userManagementServiceAction;
	}
	public String getDbCode() {
		return dbCode;
	}
	public void setDbCode(String dbCode) {
		this.dbCode = dbCode;
	}
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
}
