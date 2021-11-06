/**
* @author  Muhammad Noman
* @version 1.0
* @since   2021-February-18
*/
package invoice.generation.service;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import invoice.generation.service.beans.InvoiceGenerationRequest;
import invoice.generation.service.beans.InvoiceGenerationResponse;
import invoice.generation.service.bl.AbstractInvoiceGenerationHandler;
import invoice.generation.service.utils.Constants;

public class InvoiceGenerationService {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceGenerationService.class);
	
	public InvoiceGenerationResponse generateInvoice(InvoiceGenerationRequest invoiceGenerationRequest, Connection con) throws BaseException {
		InvoiceGenerationResponse invoiceGenerationResponse = null;
		try {
			if (null == invoiceGenerationRequest) {
				return invoiceGenerationResponse;
			}
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to call invoiceGenerationService": null);
			invoiceGenerationResponse = AbstractInvoiceGenerationHandler.getInstance().generateInvoice(invoiceGenerationRequest, con);
		} catch (Exception ex) {
			logger.warn("##Exception## in user management service ..."+ex);
			throw new BaseException(ex);
		}
		return invoiceGenerationResponse;
	}
}
