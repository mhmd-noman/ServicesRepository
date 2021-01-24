package transaction.logging.service.beans;

import common.request.MainRequestObject;
import common.response.MainResponseObject;

public class TransactionLoggingRequest {
	private MainRequestObject  mainRequestObject = null;
	private MainResponseObject mainResponseObject = null;

	public MainRequestObject getMainRequestObject() {
		return mainRequestObject;
	}
	public void setMainRequestObject(MainRequestObject mainRequestObject) {
		this.mainRequestObject = mainRequestObject;
	}
	public MainResponseObject getMainResponseObject() {
		return mainResponseObject;
	}
	public void setMainResponseObject(MainResponseObject mainResponseObject) {
		this.mainResponseObject = mainResponseObject;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionLoggingRequest [mainRequestObject=");
		builder.append(mainRequestObject);
		builder.append(", mainResponseObject=");
		builder.append(mainResponseObject);
		builder.append("]");
		return builder.toString();
	}
}
