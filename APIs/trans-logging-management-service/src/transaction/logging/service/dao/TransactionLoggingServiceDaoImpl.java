package transaction.logging.service.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.manager.methods.AbstractCommonDbMethods;
import transaction.logging.service.TransactionLoggingService;
import transaction.logging.service.beans.TransactionLoggingRequest;

public class TransactionLoggingServiceDaoImpl extends AbstractTransactionLoggingServiceDao {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingService.class);

	@Override
	public void logTransaction(TransactionLoggingRequest transactionLoggingRequest, Connection connection) {
		List<Object> paramList = null;
		if (null != transactionLoggingRequest) {
			paramList = new ArrayList<>();
			paramList.add(transactionLoggingRequest.getMainRequestObject().getServiceId());
			if (null != transactionLoggingRequest.getMainRequestObject().getUserInfo()) {
				paramList.add(transactionLoggingRequest.getMainRequestObject().getUserInfo().getUsername());
			}
			if (null != transactionLoggingRequest.getMainRequestObject().getOrderInfo()) {
				paramList.add(transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderId());
			}
			paramList.add(transactionLoggingRequest.getMainResponseObject().getResponseCode());
			paramList.add(transactionLoggingRequest.getMainResponseObject().getResponseDesc());
			paramList.add(transactionLoggingRequest.getMainResponseObject().getOrders().get(0).getOrderOrgAmount());
			paramList.add(transactionLoggingRequest.getMainResponseObject().getOrders().get(0).getOrderRtlAmount());
			paramList.add(transactionLoggingRequest.getMainResponseObject().getOrders().get(0).getOrderCalcDiscount());
			logger.info(logger.isInfoEnabled() ? "Going to log transaction by using query: " +AbstractTransactionLoggingServiceDao.LOG_TRANSACTION+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractTransactionLoggingServiceDao.LOG_TRANSACTION, paramList, connection);
		}
	}
}
