package user.management.services.beans;

import java.util.List;

import common.beans.User;

public class UsersManagementResponse {
	private String responseCode = null;
	private String responseDesc = null;
	private List<User> users = null;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}
}
