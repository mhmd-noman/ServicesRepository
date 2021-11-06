package invoice.generation.service.bl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.beans.Product;
import common.exception.handling.BaseException;
import common.utilities.constants.ResponseCodes;
import common.utilities.methods.Utils;
import invoice.generation.service.beans.InvoiceGenerationRequest;
import invoice.generation.service.beans.InvoiceGenerationResponse;
import invoice.generation.service.utils.Constants;

public class GenerateInvoice extends AbstractInvoiceGenerationHandler {
	private static final Logger logger = LoggerFactory.getLogger(GenerateInvoice.class);
	private String invoiceId = null;
	private Double totalAmount = 0.0;
	
	public InvoiceGenerationResponse generateInvoice(InvoiceGenerationRequest invoiceGenerationRequest,  Connection connection) throws BaseException {
		Font headerFont = null;
		Font basicFont = null;
		Font tableFont = null;
		InvoiceGenerationResponse invoiceGenerationResponse = null;
		
		try {
			invoiceGenerationResponse = new InvoiceGenerationResponse();
			headerFont = getHeaderFont(invoiceGenerationRequest);
			basicFont = getBasicFont(invoiceGenerationRequest);
			tableFont = getTableFont(invoiceGenerationRequest);
			
			//CommonUtils.formatDecimalValue((((requestedExchRate / bankExchangeRate) - 1) * 100), CommonConstants.AMOUNT_FORMATTER_PATTERN)
			if (null == invoiceGenerationRequest) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for Invoice generation ... ": null);
				invoiceGenerationResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				invoiceGenerationResponse.setResponseDesc("No Invoice Requested Content has been passed!");
				return invoiceGenerationResponse;
			}
			
			if (null == invoiceGenerationRequest.getInvoice()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no request content has been passed for Invoice generation ... ": null);
				invoiceGenerationResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				invoiceGenerationResponse.setResponseDesc("No Invoice Requested Content has been passed!");
				return invoiceGenerationResponse;
			}

			if (null == invoiceGenerationRequest.getInvoice().getOrder()) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no order content has been passed for Invoice generation ... ": null);
				invoiceGenerationResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				invoiceGenerationResponse.setResponseDesc("No Invoice's Order Content has been passed!");
				return invoiceGenerationResponse;
			}

			if (Utils.isNullOrEmptyCollection(invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts())) {
				logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Can't proceed as no products are found in order, passed for Invoice generation ... ": null);
				invoiceGenerationResponse.setResponseCode(ResponseCodes.INVALID_TRANS);
				invoiceGenerationResponse.setResponseDesc("No Products are found in order passed!");
				return invoiceGenerationResponse;
			}
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to get Invoice Configurations ... ": null);
			//AbstractInvoiceGenerationServiceDao.getInstance().getInvoiceConfigs(invoiceGenerationRequest, connection);
			
			logger.info(logger.isInfoEnabled() ? Constants.SERVICE_NAME + "Going to generate Invoice ... ": null);
			generateInvoice(invoiceGenerationRequest, headerFont, basicFont, tableFont);
			invoiceGenerationResponse.setInvoiceId(invoiceId);
			invoiceGenerationResponse.setResponseCode(ResponseCodes.SUCCESS);
			invoiceGenerationResponse.setResponseDesc(ResponseCodes.SUCCESS_DESCRIPTION);
			return invoiceGenerationResponse;
		} catch (Exception ex) {
			logger.warn("##Exception## while generating invoice ..."+ex);
			throw new BaseException(ex);
		}
	}
	

	public void generateInvoice(InvoiceGenerationRequest invoiceGenerationRequest, Font headerFont, Font basicFont, Font tableFont) throws DocumentException, URISyntaxException, IOException {
		Document document = null;
		PdfPTable table = null;
		PdfPTable tableForLastRow = null;
		// Document
		document = getDocument(invoiceGenerationRequest, headerFont, basicFont);
		// Table 
		table = getTable(invoiceGenerationRequest);
		//addTableHeader(invoiceGenerationRequest, table, headerFont);
		addDynamicTableHeaders(invoiceGenerationRequest, table);
		addTblBody(invoiceGenerationRequest, table, tableFont);	
		tableForLastRow = getTableForLastRow(invoiceGenerationRequest, tableFont);
		document.add(table);
		document.add(tableForLastRow);
		document.close();
	}
	
	private Document getDocument(InvoiceGenerationRequest invoiceGenerationRequest, Font headerFont, Font basicTextFont) throws DocumentException, IOException {
		PdfWriter pdfWriter = null;
		invoiceId = "invoice_" +invoiceGenerationRequest.getInvoice().getOrder().getOrderId();
		setHeightUnits(invoiceGenerationRequest);
		Document document = new Document(getPageSize(invoiceGenerationRequest));
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(invoiceGenerationRequest.getInvoice().getDestPath() + "\\" + invoiceId + ".pdf" ));
		document.open();

		// Adding Logo 
		document = getInvoiceLogo(invoiceGenerationRequest, document);
		
	    // Invoice Header Layout  
		addInvoiceHeader(invoiceGenerationRequest, document, headerFont);
		addCustomerTrackerId(invoiceGenerationRequest, document, basicTextFont);
		document.add(getParagraph(new Date(new java.util.Date().getTime()).toString(), basicTextFont));
		document.add(new Paragraph("\n"));
		
		// Invoice Metadata:
		document.addCreationDate();
		document.addAuthor(invoiceGenerationRequest.getInvoice().getAuthor());
		document.addCreator(invoiceGenerationRequest.getInvoice().getCreator());
		document.addTitle(invoiceGenerationRequest.getInvoice().getTitle());
	    document.addSubject(invoiceGenerationRequest.getInvoice().getSubject());
	    addFooter(pdfWriter, document, basicTextFont, invoiceGenerationRequest.getInvoice().getFooter());
	    return document;
	}
	
	private Rectangle getPageSize(InvoiceGenerationRequest invoiceGenerationRequest) {
		return new Rectangle(invoiceGenerationRequest.getInvoice().getWidthUnits(), invoiceGenerationRequest.getInvoice().getHeightUnits());
	}
	
	private void addInvoiceHeader(InvoiceGenerationRequest invoiceGenerationRequest, Document document, Font headerFont) throws DocumentException {
		document.add(getParagraph(invoiceGenerationRequest.getInvoice().getHeader(), headerFont));
	}
	
	private void addCustomerTrackerId(InvoiceGenerationRequest invoiceGenerationRequest, Document document, Font basicTextFont) throws DocumentException {
		document.add(getParagraph(Constants.CUSTOMER_ID + invoiceGenerationRequest.getInvoice().getOrder().getOrderId().toString(), basicTextFont));
	}
	
	private Paragraph getParagraph(String content, Font paragrpFont) {
		return new Paragraph(content, paragrpFont);
	}
	
	public PdfPTable getTable(InvoiceGenerationRequest invoiceGenerationRequest) {
		PdfPTable table = new PdfPTable(invoiceGenerationRequest.getInvoice().getTableColsConfig());
		table.setWidthPercentage(invoiceGenerationRequest.getInvoice().getTableWidthPercentage());
		table.setPaddingTop(invoiceGenerationRequest.getInvoice().getTablePaddingTop());
		return table;
	}
	
	public PdfPTable getTableForLastRow(InvoiceGenerationRequest invoiceGenerationRequest, Font tblFont) {
		float leftSide = 0.0f;
		float rightSide = 0.0f;
		for (float f : invoiceGenerationRequest.getInvoice().getTableColsConfig()) {
			leftSide += f;
		}
		rightSide = invoiceGenerationRequest.getInvoice().getTableColsConfig()[invoiceGenerationRequest.getInvoice().getTableColsConfig().length-1];
		leftSide = leftSide - rightSide;
		PdfPTable table = new PdfPTable(new float[] {leftSide, rightSide});
		table.setWidthPercentage(invoiceGenerationRequest.getInvoice().getTableWidthPercentage());
		table.setPaddingTop(invoiceGenerationRequest.getInvoice().getTablePaddingTop());
		table.addCell(getCell(invoiceGenerationRequest, "Total", tblFont, false));
		if (!Utils.validateIfNullOrInvalidDouble(totalAmount)) {
			table.addCell(getCell(invoiceGenerationRequest, totalAmount.toString(), tblFont, false));
		} else {
			table.addCell(getCell(invoiceGenerationRequest, Constants.PRICE_NOT_FOUND, tblFont, false));
		}
		return table;
	}
	
	private void addDynamicTableHeaders(InvoiceGenerationRequest invoiceGenerationRequest, PdfPTable table) {
		Font headerFont = getFont(invoiceGenerationRequest.getInvoice().getHeadingFont(), invoiceGenerationRequest.getInvoice().getHeadingTextSize(), invoiceGenerationRequest.getInvoice().getHeadingFontStrength());
	    String [] headers = Utils.convertCommaSeparatedStringToStringArray(invoiceGenerationRequest.getInvoice().getTableHeaders());
	    for (String header : headers) {
	    	table.addCell(getCell(invoiceGenerationRequest, header, headerFont, true));
	    }
	}
	
	private PdfPCell getCell(InvoiceGenerationRequest invoiceGenerationRequest, String content, Font font, boolean isHeaderCell) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setHorizontalAlignment(getAlignment(invoiceGenerationRequest.getInvoice().getCellHorizontalAlignment()));
		cell.setVerticalAlignment(getAlignment(invoiceGenerationRequest.getInvoice().getCellVerticalAlignment()));
		if (isHeaderCell) {
			cell.setBackgroundColor(getHeaderBackgroundColor(invoiceGenerationRequest.getInvoice().getTableHeaderBckgrdColor()));
		}
		return cell;
	}

	private void addTblBody(InvoiceGenerationRequest invoiceGenerationRequest, PdfPTable table, Font tblFont) {
		//Font tblFont = getFont(invoiceGenerationRequest.getTableFont(), invoiceGenerationRequest.getTableTextSize(), invoiceGenerationRequest.getTableFontStrength());
		for (Product product : invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts()) {
			// Name Cell
			table.addCell(getCell(invoiceGenerationRequest, product.getName(), tblFont, false));
			// Quantity Cell
			if (!Utils.validateIfNullOrInvalidInteger(product.getOrderedQuantity())) {
				table.addCell(getCell(invoiceGenerationRequest, product.getOrderedQuantity().toString(), tblFont, false));
			} else {
				table.addCell(getCell(invoiceGenerationRequest, Constants.ZERO_QUANTITY, tblFont, false));
			}
			// Price Cell
			if (!Utils.validateIfNullOrInvalidDouble(product.getRtlPrice())) {
				table.addCell(getCell(invoiceGenerationRequest, product.getRtlPrice().toString(), tblFont, false));
				totalAmount += product.getRtlPrice();
			} else {
				table.addCell(getCell(invoiceGenerationRequest, Constants.PRICE_NOT_FOUND, tblFont, false));
			}
		}
	}
	
	// This method needs improvements ...
	public Document getInvoiceLogo(InvoiceGenerationRequest invoiceGenerationRequest, Document document) throws DocumentException, IOException {
	    PdfPTable logotable = new PdfPTable(2);
	    logotable.setWidthPercentage(100);
		logotable.setWidths(new int[]{1, 2});
	    
		// Logo Image ...
		Image img = Image.getInstance(invoiceGenerationRequest.getInvoice().getLogo());
	    img.scalePercent(invoiceGenerationRequest.getInvoice().getLogoScalePercentage());
		PdfPCell cellLogo = new PdfPCell(img, true);
	    if (Constants.ALIGN_LEFT.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoVerticalAlignment())) {
	    	cellLogo.setVerticalAlignment(Element.ALIGN_LEFT);
	    } else if (Constants.ALIGN_RIGHT.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoVerticalAlignment())) {
	    	cellLogo.setVerticalAlignment(Element.ALIGN_RIGHT);
	    } else if (Constants.ALIGN_CENTER.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoVerticalAlignment())) {
	    	cellLogo.setVerticalAlignment(Element.ALIGN_CENTER);
	    } else {
	    	// Default
	    	cellLogo.setVerticalAlignment(Element.ALIGN_LEFT);
	    }
	    
	    if (Constants.ALIGN_LEFT.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoHorizontalAlignment())) {
	    	cellLogo.setHorizontalAlignment(Element.ALIGN_LEFT);
	    } else if (Constants.ALIGN_RIGHT.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoHorizontalAlignment())) {
	    	cellLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    } else if (Constants.ALIGN_CENTER.equalsIgnoreCase(invoiceGenerationRequest.getInvoice().getLogoHorizontalAlignment())) {
	    	cellLogo.setHorizontalAlignment(Element.ALIGN_CENTER);
	    } else {
	    	// Default
	    	cellLogo.setVerticalAlignment(Element.ALIGN_LEFT);
	    }
	   
	    cellLogo.setPaddingRight(5f);
	    cellLogo.setBorder(Rectangle.NO_BORDER);
	    logotable.addCell(cellLogo);
	    
	    // Text
	    PdfPCell cellText = new PdfPCell();
	    Paragraph p = new Paragraph(invoiceGenerationRequest.getInvoice().getLogoHeader(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD));
	    //p.setAlignment(Element.ALIGN_RIGHT);
	    cellText.addElement(p);
	    cellText.setVerticalAlignment(Element.ALIGN_CENTER);
	    cellText.setBorder(Rectangle.NO_BORDER);
	    logotable.addCell(cellText);
	    document.add(logotable);
	    return document;
	}
	
	private void setHeightUnits(InvoiceGenerationRequest invoiceGenerationRequest) {
		if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 10) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(400f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 15) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(450f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 20) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(500f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 25) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(550f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 25) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(600f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 30) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(650f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 35) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(700f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 40) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(750f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 45) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(800f);
		} else if (invoiceGenerationRequest.getInvoice().getOrder().getOrderedProducts().size() <= 50) {
			invoiceGenerationRequest.getInvoice().setHeightUnits(850f);
		} else {
			// default
			invoiceGenerationRequest.getInvoice().setHeightUnits(1000f);
		}
	}
	
	private int getAlignment(String alignment) {
		if (Constants.ALIGN_LEFT.equalsIgnoreCase(alignment)) {
			return Element.ALIGN_LEFT;
		} else if (Constants.ALIGN_RIGHT.equalsIgnoreCase(alignment)) {
			return Element.ALIGN_RIGHT;
		} else if (Constants.ALIGN_CENTER.equalsIgnoreCase(alignment)) {
			return Element.ALIGN_CENTER;
		} else if (Constants.ALIGN_TOP.equalsIgnoreCase(alignment)) {
			return Element.ALIGN_TOP;
		} else if (Constants.ALIGN_JUSTIFIED.equalsIgnoreCase(alignment)) {
			return Element.ALIGN_JUSTIFIED;
		} else {
			// default
			return Element.ALIGN_CENTER;
		}
	}
	
	private BaseColor getHeaderBackgroundColor(String headerBackgroundColor) {
		BaseColor baseColor = null;
		if (Constants.COLOR_LIGHT_GRAY.equalsIgnoreCase(headerBackgroundColor)) {
			baseColor= BaseColor.LIGHT_GRAY;
		} else if (Constants.COLOR_DARK_GRAY.equalsIgnoreCase(headerBackgroundColor)) {
			baseColor = BaseColor.DARK_GRAY;
		} else if (Constants.COLOR_WHITE.equalsIgnoreCase(headerBackgroundColor)) {
			baseColor = BaseColor.WHITE;
		} else {
			//default
			baseColor = BaseColor.LIGHT_GRAY;
		}
		return baseColor;
	}
	
	private Font getHeaderFont(InvoiceGenerationRequest invoiceGenerationRequest) {
		return getFont(invoiceGenerationRequest.getInvoice().getHeadingFont(), invoiceGenerationRequest.getInvoice().getHeadingTextSize(), invoiceGenerationRequest.getInvoice().getHeadingFontStrength());
	}
	
	private Font getBasicFont(InvoiceGenerationRequest invoiceGenerationRequest) {
		return getFont(invoiceGenerationRequest.getInvoice().getBasicFont(), invoiceGenerationRequest.getInvoice().getBasicTextSize(), invoiceGenerationRequest.getInvoice().getBasicFontStrength());
	}
	
	private Font getTableFont(InvoiceGenerationRequest invoiceGenerationRequest) {
		return getFont(invoiceGenerationRequest.getInvoice().getTableFont(), invoiceGenerationRequest.getInvoice().getTableTextSize(), invoiceGenerationRequest.getInvoice().getTableFontStrength());
	}

	public Font getFont(String font, float size, String style) {
		Font reqFont = null;
		if (Constants.FONT_COURIER.equalsIgnoreCase(font)) {
			if (Constants.FONT_STYLE_NORMAL.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.COURIER, size, Font.NORMAL);
			} else if (Constants.FONT_STYLE_BOLD.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.COURIER, size, Font.BOLD);
			} else if (Constants.FONT_STYLE_ITALIC.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.COURIER, size, Font.ITALIC);
			} else {
				// Default
				reqFont = FontFactory.getFont(FontFactory.COURIER, size, Font.NORMAL);
			}
		} else if (Constants.FONT_HELVETICA.equalsIgnoreCase(font)) {
			if (Constants.FONT_STYLE_NORMAL.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.HELVETICA, size, Font.NORMAL);
			} else if (Constants.FONT_STYLE_BOLD.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.HELVETICA, size, Font.BOLD);
			} else if (Constants.FONT_STYLE_ITALIC.equalsIgnoreCase(style)) {
				reqFont = FontFactory.getFont(FontFactory.HELVETICA, size, Font.ITALIC);
			} else {
				reqFont = FontFactory.getFont(FontFactory.HELVETICA, size, Font.NORMAL);
			}
		} else {
			// Default Case if config not found ...
			reqFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		}
		return reqFont;
	}
	
//	public void onStartPage(PdfWriter writer, Document document) {
//  ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
//  ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);
//}
//
	private void addFooter(PdfWriter writer, Document document, Font basicFont, String footerContent) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(footerContent, basicFont), 110, 30, 0);
		//ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber()), 550, 30, 0);
	}
}
