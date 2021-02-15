package invoice.generation.service.beans;

import common.beans.Invoice;

public class InvoiceGenerationRequest {
	private Invoice invoice = null;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvoiceGenerationRequest [invoice=");
		builder.append(invoice);
		builder.append("]");
		return builder.toString();
	}
}
