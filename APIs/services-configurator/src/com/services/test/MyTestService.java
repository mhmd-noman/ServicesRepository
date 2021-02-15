package com.services.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.services.configurator.ServicesConfigurator;

import common.beans.Invoice;
import common.beans.Order;
import common.beans.Product;
import common.beans.User;
import common.exception.handling.BaseException;
import common.request.MainRequestObject;
import common.response.MainResponseObject;  

public class MyTestService {
	public static final String dbCode = "UFN";
	public static void main(String [] args) throws DocumentException, URISyntaxException, IOException, BaseException {
		//getUsers();
		//getProducts();
		//addProduct();
		//deleteProduct();
		//updateProduct();
		//getOrders();
		//placeOrder();
		
		  
		// from,password,to,subject,message
		//Mailer.send("lastchance934@gmail.com", "numan41752666", "muhammad.nauman54@outlook.com", "hello javatpoint", "How r u?");
		// change from, password and to 
		//creatingInvoiceHavingTable();
		generateInvoice();
		
	}
	
	public static void generateInvoice() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		
		Order order = getOrder();
		Invoice invoice = new Invoice();
		invoice.setDestPath("C:\\apps\\invoices\\");
		// Page Size
		invoice.setWidthUnits(250f);
		invoice.setHeightUnits(432f);
		
		// 18 products can land on size of 432f
		// Logo
		invoice.setLogo("C:\\apps\\invoices\\logo.png");
		invoice.setLogoHeader("iTeamVision's POS");
		invoice.setLogoHorizontalAlignment("ALIGN_LEFT");
		invoice.setLogoVerticalAlignment("ALIGN_LEFT");
		invoice.setLogoBorder(0);
		invoice.setLogoScalePercentage(6f);
		// MetaData
		invoice.setAuthor("M Noman");
		invoice.setCreator("iTeamVision");
		invoice.setTitle("POS Invoice");
		invoice.setSubject("Creating POS Invoice for Order id: 1");
		// Font
		invoice.setBasicFont("COURIER");
		invoice.setBasicFontStrength("NORMAL");
		invoice.setBasicTextSize(8f);
		invoice.setHeadingFont("COURIER");
		invoice.setHeadingFontStrength("BOLD");
		invoice.setHeadingTextSize(10f);
		invoice.setTableFont("COURIER");
		invoice.setTableFontStrength("NORMAL");
		invoice.setTableTextSize(8f);
		
		PdfPTable table = new PdfPTable(new float[] {8, 3, 4});
		table.setWidthPercentage(110f);
		table.setPaddingTop(10f);
		
		invoice.setNumberOfColumns(3);
		invoice.setTableHeaders("Name,Qty,Price");
		invoice.setTableColsConfig(new float[] {8, 3, 4});
		invoice.setTableHeaderBckgrdColor("DARK_GREY");
		invoice.setTablePaddingTop(10f);
		invoice.setTableHeadingBorder(0);
		invoice.setTableWidthPercentage(110f);
		invoice.setTableBorder(0f); // it's not working
		invoice.setCellHorizontalAlignment("ALIGN_CENTER");
		invoice.setCellHorizontalAlignment("ALIGN_CENTER");
		
		invoice.setHeader("Welcome to iTeamVision's POS");
		invoice.setFooter("POS - Powered By iTeamVision");
		invoice.setOrder(order);
		
		mainRequestObject.setInvoiceInfo(invoice);
		mainResponseObject = servicesConfigurator.generateInvoice(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
	}
	
	public static Order getOrder() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Nitro Tech");
		product1.setOrderedQuantity(5);
		product1.setRtlPrice(1800.00);
		product1.setOrgPrice(2000.00);
		product1.setDiscount(5.00);
		
		Product product2 = new Product();
		product2.setId(2);
		product2.setName("ON Whey");
		product2.setOrderedQuantity(2);
		product2.setOrgPrice(2000.00);
		product2.setDiscount(5.00);
		
		products.add(product1);
		products.add(product2);
		
		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
//		products.add(getProduct());
		
		
		
		Order order = new Order();
		order.setOrderId(2);
		order.setArea("Canal");
		order.setState("Punjab");
		order.setCity("Lahore");
		order.setCountry("Pakistan");
		order.setCustName("Damon");
		order.setCustEmail("damon@gmail.com");
		order.setCustPhone("032412345698");
		order.setCustPhone2("032456958691");
		order.setOrderCalcDiscount(5.00);
		order.setOrderDescription("Testing Order Placement!");
		order.setCustAddress1("Address1");
		order.setCustAddress2("Address2");
		//order.setExpiryDate(new Date(System.currentTimeMillis()));
		//order.setMfgDate(new Date(System.currentTimeMillis()));
		order.setOrderOrgAmount(2000.00);
		order.setOrderRtlAmount(1600.00);
		order.setOrderStatus("I");
		order.setOrderedProducts(products);
		return order;
	}
	
	private static Product getProduct() {
		Product product2 = new Product();
		product2.setId(2);
		product2.setName("ON Whey");
		product2.setOrderedQuantity(2);
		product2.setOrgPrice(2000.00);
		product2.setDiscount(5.00);
		product2.setRtlPrice(1500.00);
		return product2;
	}
	
	public static void creatingInvoice() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World", font);

		document.add(chunk);
		document.close();
	}
	
//	public static void generatingBarCodes() {
//		PdfDocument pdfDoc = new PdfDocument(new PdfWriter("C:\\apps\\invoices\\barCodeTest.pdf"));
//	    Document doc = new Document(pdfDoc);    
//		
//		    Rectangle pagesize = new Rectangle(250, 432);
//		    Document doc = new Document(pagesize);
//			PdfWriter.getInstance(doc, new FileOutputStream("C:\\apps\\invoices\\barCodeTest.pdf"));
//
//		    
//		    String code = "675-FH-A12";
//		    Barcode128 code128 = new Barcode128(pdfDoc);
//		    code128.setFont(null);
//		    code128.setCode(code);
//		    code128.setCodeType(Barcode128.CODE128);
//		 
//		    Table table = new Table(2);
//		    table.addCell("Add text under the barcode:");
//		 
//		    Image code128Image = new Image(code128.createFormXObject(pdfDoc)).setAutoScale(true);
//		    Paragraph paragraph = new Paragraph("Student code:" + code).setTextAlignment(TextAlignment.CENTER);
//		 
//		    Cell cell = new Cell();
//		    cell.add(code128Image);
//		    cell.add(paragraph);
//		    table.addCell(cell);
//		 
//		    doc.add(table);
//		    doc.close();
//	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void creatingInvoiceHavingTable() throws DocumentException, URISyntaxException, IOException {
		//Rectangle pagesize = new Rectangle(612, 864);
		//Rectangle pagesize = new Rectangle(306, 432);
		Rectangle pagesize = new Rectangle(250, 432);
		Document document = new Document(pagesize);
		PdfWriter.getInstance(document, new FileOutputStream("C:\\apps\\invoices\\testInvoice.pdf"));

		document.open();
		
		// Adding Logo Image
		PdfPTable logotable = new PdfPTable(2);
		logotable.setWidthPercentage(100);
		logotable.setWidths(new int[]{1, 2});
		logotable.addCell(createLogoImageCell("C:\\apps\\invoices\\logo.png"));
		logotable.addCell(createLogoTextCell("POS - Powered By iSpecterTeam"));
	    document.add(logotable);
		
		// Adding Logo Image
		//Image img = Image.getInstance("C:\\apps\\invoices\\logo.png");
		//img.scalePercent(8);
		//document.add(img);
		
		// Intro Message
		document.add(new Paragraph("Welcome to ISpecterTeam's POS", FontFactory.getFont(FontFactory.COURIER, 8)));
		document.add(new Paragraph("Your Customer Id: 2345433", FontFactory.getFont(FontFactory.COURIER, 8)));
		document.add(new Paragraph(new Date(new java.util.Date().getTime()).toString(), FontFactory.getFont(FontFactory.COURIER, 8)));
		document.add(new Paragraph("\n"));
		
		// Adding Metadata:
		document.addCreationDate();
		document.addAuthor("M Noman");
		document.addCreator("iSpecterTeam");
		document.addTitle("How to create PDF document in Java");
	    document.addSubject("Writing to create invoice in order to process POS Order");
	 
	    // first column to be three times as wide as the second and third column
		PdfPTable table = new PdfPTable(new float[] {8, 3, 4});
		table.setWidthPercentage(110f);
		table.setPaddingTop(10f);
		addTableHeader(table);
		//addRows(table);
		addCustomRows(table);
		addCustomRows(table);
		addCustomRows(table);
		addCustomRows(table);
		addCustomRows(table);
		
		PdfPTable table1 = new PdfPTable(new float[] {11, 4});
		table1.setWidthPercentage(110f);
		addRows(table1);
		
		
		document.add(table);
		document.add(table1);
		
		
		document.close();
		System.out.println("Invoice Processed!!!");
	}
	
	private static void addTableHeader(PdfPTable table) {
		Font fontH1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
	    Stream.of("Name", "Qty", "Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell(new Phrase("row 2, col 2", fontH1));
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private static void addRows(PdfPTable table) {
		Font fontH1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
		// Product Name
		PdfPCell horizontalAlignCell1 = new PdfPCell(new Phrase("Total ", fontH1));
		horizontalAlignCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		//horizontalAlignCell1.setPadding(2f);
		table.addCell(horizontalAlignCell1);
		
		// Product Quantity 
		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("135000 Rs", fontH1));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//horizontalAlignCell.setPadding(2f);
		table.addCell(horizontalAlignCell);
	}
	
	
	private static void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
		Font fontH1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL);
		Image img = Image.getInstance("C:\\apps\\invoices\\logo.png");
		img.scalePercent(8);
		
		// Product Name
		PdfPCell horizontalAlignCell1 = new PdfPCell(new Phrase("Nitro Tech - 5lbs", fontH1));
		horizontalAlignCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		horizontalAlignCell1.setVerticalAlignment(Element.ALIGN_CENTER);
		//horizontalAlignCell1.setPadding(2f);
		table.addCell(horizontalAlignCell1);
		
		// Product Quantity 
		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("3", fontH1));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		horizontalAlignCell.setVerticalAlignment(Element.ALIGN_CENTER);
		//horizontalAlignCell.setPadding(2f);
		table.addCell(horizontalAlignCell);
		
		// Product Prices
		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("17000 Rs", fontH1));
		verticalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		verticalAlignCell.setVerticalAlignment(Element.ALIGN_CENTER);
		//verticalAlignCell.setPadding(2f);
		table.addCell(verticalAlignCell);
	}
	
	public static PdfPCell createLogoImageCell(String path) throws DocumentException, IOException {
	    Image img = Image.getInstance(path);
	    img.scalePercent(6);
	    PdfPCell cell = new PdfPCell(img, true);
	    cell.setVerticalAlignment(Element.ALIGN_LEFT);
	    cell.setPaddingRight(5f);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}
	
	public static PdfPCell createLogoTextCell(String text) throws DocumentException, IOException {
	    PdfPCell cell = new PdfPCell();
	    Paragraph p = new Paragraph(text, FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD));
	    //p.setAlignment(Element.ALIGN_RIGHT);
	    cell.addElement(p);
	    cell.setVerticalAlignment(Element.ALIGN_CENTER);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void insertingImageInInvoice() throws DocumentException, MalformedURLException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextImageExample.pdf"));
		document.open();
		Image img = Image.getInstance("C:\\apps\\invoices\\logo.png");
		document.add(img);

		document.close();
	}
	
	public static void placeOrder() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		
		List<Product> products = new ArrayList<>();
		
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Nitro Tech");
		product1.setOrderedQuantity(5);
		product1.setRtlPrice(1800.00);
		product1.setOrgPrice(2000.00);
		product1.setDiscount(5.00);
		
		Product product2 = new Product();
		product2.setId(2);
		product2.setName("ON Whey");
		product2.setOrderedQuantity(2);
		product2.setOrgPrice(2000.00);
		product2.setDiscount(5.00);
		
		products.add(product1);
		products.add(product2);
		
		Order order = new Order();
		order.setArea("Canal");
		order.setState("Punjab");
		order.setCity("Lahore");
		order.setCountry("Pakistan");
		order.setCustName("Damon");
		order.setCustEmail("damon@gmail.com");
		order.setCustPhone("032412345698");
		order.setCustPhone2("032456958691");
		order.setOrderCalcDiscount(5.00);
		order.setOrderDescription("Testing Order Placement!");
		order.setCustAddress1("Address1");
		order.setCustAddress2("Address2");
		//order.setExpiryDate(new Date(System.currentTimeMillis()));
		//order.setMfgDate(new Date(System.currentTimeMillis()));
		order.setOrderOrgAmount(2000.00);
		order.setOrderRtlAmount(1600.00);
		order.setOrderStatus("I");
		order.setOrderedProducts(products);
		
		mainRequestObject.setOrderInfo(order);
		
		mainResponseObject = servicesConfigurator.placeOrder(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
	}
	
	public static void getOrders() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.getOrders(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
		if (null != mainResponseObject.getOrders()) {
			for (Order order : mainResponseObject.getOrders()) {
				System.out.println(" ---- Orders -----");
				System.out.println(order);
				System.out.println("\n");
			}
		}
	}
	
	public static void getUsers() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		mainResponseObject = servicesConfigurator.getUsers(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
		for (User user : mainResponseObject.getUsers()) {
			System.out.println(" ---- Users -----");
			System.out.println(user);
			System.out.println("\n\n");
		}
	}
	
	public static void getProducts() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(2);
		mainRequestObject.setIds(ids);
		mainRequestObject.setDbCode(dbCode);
		mainRequestObject.setFetchOutOfStockProducts(true);
		mainResponseObject = servicesConfigurator.getProducts(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
		for (Product product : mainResponseObject.getProducts()) {
			System.out.println(" ---- Products -----");
			System.out.println(product);
			System.out.println("\n");
		}
	}
	
	public static void addProduct() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		
		Product product = new Product();
		Date expDate;
		Date mfgDate;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DATE, 24);
        cal.set(Calendar.YEAR, 2022);
        expDate = cal.getTime();
        
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 01);
        cal.set(Calendar.YEAR, 2020);
        mfgDate = cal.getTime();
        
		product.setName("ON Whey");
		product.setCategory("Protein");
		product.setDescription("Optimum Nutrition");
		product.setDirectiontoUse("1 Serving Per Day");
		product.setBarCode("4455663321");
		product.setDiscount(5.00);
		product.setExpiryDate(expDate);
		product.setFlavour("Vanilla");
		product.setMfgDate(mfgDate);
		product.setNetPrice(12500.00);
		product.setOrgPrice(14800.00);
		product.setRtlPrice(12500.00);
		product.setServings("30");
		product.setWeight("5");
		
		mainRequestObject.setDbCode(dbCode);
		mainRequestObject.setProductInfo(product);
		mainResponseObject = servicesConfigurator.addProduct(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
	}
	
	public static void deleteProduct() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		
		Product product = new Product();
		product.setId(2);
		mainRequestObject.setProductInfo(product);
		mainResponseObject = servicesConfigurator.deleteProduct(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
		
	}
	
	public static void updateProduct() throws BaseException {
		ServicesConfigurator servicesConfigurator = new ServicesConfigurator();
		MainRequestObject mainRequestObject = new MainRequestObject();
		MainResponseObject mainResponseObject = null;
		mainRequestObject.setDbCode(dbCode);
		
		Product product = new Product();
		product.setId(2);
		product.setName("ON my Whey");
		mainRequestObject.setProductInfo(product);
		mainResponseObject = servicesConfigurator.updateProduct(mainRequestObject);
		System.out.println("Response Code: "+ mainResponseObject.getResponseCode());
		System.out.println("Response Desc: "+ mainResponseObject.getResponseDesc());
		
	}
}
