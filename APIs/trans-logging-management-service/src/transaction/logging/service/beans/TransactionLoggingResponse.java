package transaction.logging.service.beans;

public class TransactionLoggingResponse {
	private String responseCode = null;
	private String responseDesc = null;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersManagementResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDesc=");
		builder.append(responseDesc);
		builder.append("]");
		return builder.toString();
	}
}
