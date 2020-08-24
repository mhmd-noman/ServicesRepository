package user.management.services.beans;

import user.management.services.utils.UserManagementServiceAction;

public class UsersManagementRequest {
	private String username = null;
	private String email = null;
	private String firstName = null;
	private String lastName = null;
	private String password = null;
	private String phone = null;
	private String enabled = null;
	private UserManagementServiceAction userManagementServiceAction = null;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserManagementServiceAction getUserManagementServiceAction() {
		return userManagementServiceAction;
	}
	public void setUserManagementServiceAction(UserManagementServiceAction userManagementServiceAction) {
		this.userManagementServiceAction = userManagementServiceAction;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersManagementRequest [username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", userManagementServiceAction=");
		builder.append(userManagementServiceAction);
		builder.append("]");
		return builder.toString();
	}
}
