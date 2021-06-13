package api.communicator.beans;

public class ApiInfo {
	private Integer id = null;
	private String dbCode = null;
	private String name = null;
	private String url = null;
	private String user = null;
	private String apiBaseUri = null;
	private String contentType = null;
	private String tokenPrefix = null;
	private String token = null;
	private String authUsername = null;
	private String authPassword = null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDbCode() {
		return dbCode;
	}
	public void setDbCode(String dbCode) {
		this.dbCode = dbCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getApiBaseUri() {
		return apiBaseUri;
	}
	public void setApiBaseUri(String apiBaseUri) {
		this.apiBaseUri = apiBaseUri;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthUsername() {
		return authUsername;
	}
	public void setAuthUsername(String authUsername) {
		this.authUsername = authUsername;
	}
	public String getAuthPassword() {
		return authPassword;
	}
	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiInfo [id=");
		builder.append(id);
		builder.append(", dbCode=");
		builder.append(dbCode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", user=");
		builder.append(user);
		builder.append(", apiBaseUri=");
		builder.append(apiBaseUri);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append(", tokenPrefix=");
		builder.append(tokenPrefix);
		builder.append(", token=");
		builder.append(token);
		builder.append(", authUsername=");
		builder.append(authUsername);
		builder.append(", authPassword=");
		builder.append(authPassword);
		builder.append("]");
		return builder.toString();
	}
}
