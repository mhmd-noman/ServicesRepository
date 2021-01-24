package transaction.logging.service.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import database.manager.methods.AbstractCommonDbMethods;
import transaction.logging.service.TransactionLoggingService;
import transaction.logging.service.beans.TransactionLoggingRequest;

public class TransactionLoggingServiceDaoImpl extends AbstractTransactionLoggingServiceDao {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingService.class);

	@Override
	public void logTransaction(TransactionLoggingRequest transactionLoggingRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != transactionLoggingRequest) {
				paramList = new ArrayList<>();
				paramList.add(transactionLoggingRequest.getMainRequestObject().getServiceId());
				if (null != transactionLoggingRequest.getMainRequestObject().getUserInfo()) {
					paramList.add(null != transactionLoggingRequest.getMainRequestObject().getUserInfo().getUsername() ? transactionLoggingRequest.getMainRequestObject().getUserInfo().getUsername(): null);
				} else {
					paramList.add(null);
				}
				if (null != transactionLoggingRequest.getMainRequestObject().getOrderInfo()) {
					paramList.add(null != transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderId() ? transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderId(): null);
				} else {
					paramList.add(null);
				}
				paramList.add(transactionLoggingRequest.getMainResponseObject().getResponseCode());
				paramList.add(transactionLoggingRequest.getMainResponseObject().getResponseDesc());
				if (null != transactionLoggingRequest.getMainRequestObject().getOrderInfo()) {
					paramList.add(null != transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderOrgAmount() ? transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderOrgAmount(): null); // org amount
					paramList.add(null != transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderRtlAmount() ? transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderRtlAmount(): null); // processed amount
					paramList.add(null != transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderCalcDiscount() ? transactionLoggingRequest.getMainRequestObject().getOrderInfo().getOrderCalcDiscount(): null); // discount 
				} else {
					paramList.add(null);
					paramList.add(null);
					paramList.add(null);
				}
				// status to be set
				logger.info(logger.isInfoEnabled() ? "Going to log transaction by using query: " +AbstractTransactionLoggingServiceDao.LOG_TRANSACTION+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractTransactionLoggingServiceDao.LOG_TRANSACTION, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while logging transaction ...");
			throw new BaseException(ex);
		}
	}
}
