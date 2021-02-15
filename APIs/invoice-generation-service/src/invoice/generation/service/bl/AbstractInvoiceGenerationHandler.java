package invoice.generation.service.bl;

import java.sql.Connection;

import common.exception.handling.BaseException;
import invoice.generation.service.beans.InvoiceGenerationRequest;
import invoice.generation.service.beans.InvoiceGenerationResponse;

public abstract class AbstractInvoiceGenerationHandler {
	public static AbstractInvoiceGenerationHandler getInstance() throws BaseException {
		return new GenerateInvoice();
	}

	public abstract InvoiceGenerationResponse generateInvoice(InvoiceGenerationRequest invoiceGenerationRequest,  Connection connection)  throws BaseException;
}