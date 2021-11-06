package invoice.generation.service.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import database.manager.methods.AbstractCommonDbMethods;
import invoice.generation.service.beans.InvoiceGenerationRequest;
import invoice.generation.service.utils.Constants;

public class InvoiceGenerationServiceDaoImpl extends AbstractInvoiceGenerationServiceDao {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceGenerationServiceDaoImpl.class);
	
	@Override
	public void getInvoiceConfigs(InvoiceGenerationRequest InvoiceGenerationRequest, Connection connection) throws BaseException {
		List<Object> paramList = null;
		try {
			if (null != InvoiceGenerationRequest) {
				paramList = new ArrayList<>();
				paramList.add(InvoiceGenerationRequest.getInvoice().getHeader());
				paramList.add(InvoiceGenerationRequest.getInvoice().getFooter());
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to generate invoice by using query: " +AbstractInvoiceGenerationServiceDao.CREATE_USER+ " with paramters: "+ paramList: null);
				AbstractCommonDbMethods.getInstance().update(AbstractInvoiceGenerationServiceDao.CREATE_USER, paramList, connection);
			}
		} catch (Exception ex) {
			logger.warn("##Exception## while creating user ..."+ex);
			throw new BaseException(ex);
		}
	}
}
