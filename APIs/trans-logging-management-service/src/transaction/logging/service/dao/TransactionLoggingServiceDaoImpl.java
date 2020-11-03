package transaction.logging.service.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.manager.methods.AbstractCommonDbMethods;
import transaction.logging.service.TransactionLoggingService;
import transaction.logging.service.beans.TransactionLoggingRequest;
import transaction.logging.service.utils.Constants;

public class TransactionLoggingServiceDaoImpl extends AbstractTransactionLoggingServiceDao {
	private static final Logger logger = LoggerFactory.getLogger(TransactionLoggingService.class);

	@Override
	public void logTransaction(TransactionLoggingRequest usersManagementRequest, Connection connection) {
		List<Object> paramList = null;
		if (null != usersManagementRequest) {
			paramList = new ArrayList<>();
			paramList.add(usersManagementRequest.getEmail());
			paramList.add(Constants.OPTION_Y.equalsIgnoreCase(usersManagementRequest.getEnabled()));
			paramList.add(usersManagementRequest.getFirstName());
			paramList.add(usersManagementRequest.getLastName());
			paramList.add(usersManagementRequest.getPassword());
			paramList.add(usersManagementRequest.getPhone());
			paramList.add(usersManagementRequest.getUsername());
			logger.info(logger.isInfoEnabled() ? "Going to log transaction by using query: " +AbstractTransactionLoggingServiceDao.CREATE_USER+ " with paramters: "+ paramList: null);
			AbstractCommonDbMethods.getInstance().update(AbstractTransactionLoggingServiceDao.CREATE_USER, paramList, connection);
		}
	}
}
