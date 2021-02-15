package com.services.invoice.generation.configurator;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;
import invoice.generation.service.InvoiceGenerationService;
import invoice.generation.service.beans.InvoiceGenerationRequest;
import invoice.generation.service.beans.InvoiceGenerationResponse;

public class InvoiceGenerationConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceGenerationConfigurator.class);

	public MainResponseObject generateInvoice(MainRequestObject mainRequestObject, Connection con) throws BaseException {
		MainResponseObject mainResponseObject = new MainResponseObject();
		InvoiceGenerationService invoiceGenerationService = new InvoiceGenerationService();
		InvoiceGenerationRequest invoiceGenerationRequest = new InvoiceGenerationRequest();
		InvoiceGenerationResponse invoiceGenerationResponse = new InvoiceGenerationResponse();
		mapRequest(mainRequestObject, invoiceGenerationRequest);
		invoiceGenerationResponse = invoiceGenerationService.generateInvoice(invoiceGenerationRequest, con);
		mapResponse(mainResponseObject, invoiceGenerationResponse);
		return mainResponseObject;
	}
	
	private void mapRequest(MainRequestObject mainRequestObject, InvoiceGenerationRequest invoiceGenerationRequest) {
		if (null != mainRequestObject && null != mainRequestObject.getInvoiceInfo()) {
			logger.info(logger.isInfoEnabled() ? "Requested content recieved for invoiceGeneration: [" +mainRequestObject+ "]": null);
			invoiceGenerationRequest.setInvoice(mainRequestObject.getInvoiceInfo());
		}
		mainRequestObject.setServiceId("INVOICE_GENERATION");
	}
	
	private void mapResponse(MainResponseObject mainResponseObject, InvoiceGenerationResponse invoiceGenerationResponse) {
		if (null != invoiceGenerationResponse) {
			logger.info(logger.isInfoEnabled() ? "Response recieved for invoiceGeneration: [" +invoiceGenerationResponse+ "]": null);
			mainResponseObject.setResponseCode(invoiceGenerationResponse.getResponseCode());
			mainResponseObject.setResponseDesc(invoiceGenerationResponse.getResponseDesc());
		}
	}
}
