package common.beans;

import java.util.Arrays;

public class Invoice {
	private String destPath = null;
	private String header = null;
	private String footer = null;
	// Page Size  //
	private Float heightUnits = null;
	private Float widthUnits = null;
	// Metadata  //
	private String title = null;
	private String author = null;
	private String creator = null;
	private String subject = null;
	// Logo  //
	private String logo = null;
	private String logoHeader = null;
	private int logoBorder = 0;
	private Float  logoScalePercentage = null;
	private String logoHorizontalAlignment = null;
	private String logoVerticalAlignment = null;
	// Fonts  //
	private String basicFont = null;
	private String headingFont = null;
	private String tableFont = null;
	private String basicFontStrength = null;
	private String headingFontStrength = null;
	private String tableFontStrength = null;
	private Float basicTextSize = null;
	private Float tableTextSize = null;
	private Float headingTextSize = null;
	// Table  //
	private String tableHeaders = null; // comma separated headers
	private float [] tableColsConfig = null;
	private Integer numberOfColumns = null;
	private String tableHeaderBckgrdColor = null; // comma separated headers
	private int tableHeadingBorder = 0;
	private Float tableWidthPercentage = null;
	private Float tablePaddingTop = null;
	private Float tableBorder = null;
	private String cellHorizontalAlignment = null;
	private String cellVerticalAlignment = null;
	// Order  //
	private Order order = null;

	public String getDestPath() {
		return destPath;
	}
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public Float getHeightUnits() {
		return heightUnits;
	}
	public void setHeightUnits(Float heightUnits) {
		this.heightUnits = heightUnits;
	}
	public Float getWidthUnits() {
		return widthUnits;
	}
	public void setWidthUnits(Float widthUnits) {
		this.widthUnits = widthUnits;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLogoHeader() {
		return logoHeader;
	}
	public void setLogoHeader(String logoHeader) {
		this.logoHeader = logoHeader;
	}
	public int getLogoBorder() {
		return logoBorder;
	}
	public void setLogoBorder(int logoBorder) {
		this.logoBorder = logoBorder;
	}
	public Float getLogoScalePercentage() {
		return logoScalePercentage;
	}
	public void setLogoScalePercentage(Float logoScalePercentage) {
		this.logoScalePercentage = logoScalePercentage;
	}
	public String getLogoHorizontalAlignment() {
		return logoHorizontalAlignment;
	}
	public void setLogoHorizontalAlignment(String logoHorizontalAlignment) {
		this.logoHorizontalAlignment = logoHorizontalAlignment;
	}
	public String getLogoVerticalAlignment() {
		return logoVerticalAlignment;
	}
	public void setLogoVerticalAlignment(String logoVerticalAlignment) {
		this.logoVerticalAlignment = logoVerticalAlignment;
	}
	public String getBasicFont() {
		return basicFont;
	}
	public void setBasicFont(String basicFont) {
		this.basicFont = basicFont;
	}
	public String getHeadingFont() {
		return headingFont;
	}
	public void setHeadingFont(String headingFont) {
		this.headingFont = headingFont;
	}
	public String getTableFont() {
		return tableFont;
	}
	public void setTableFont(String tableFont) {
		this.tableFont = tableFont;
	}
	public String getBasicFontStrength() {
		return basicFontStrength;
	}
	public void setBasicFontStrength(String basicFontStrength) {
		this.basicFontStrength = basicFontStrength;
	}
	public String getHeadingFontStrength() {
		return headingFontStrength;
	}
	public void setHeadingFontStrength(String headingFontStrength) {
		this.headingFontStrength = headingFontStrength;
	}
	public String getTableFontStrength() {
		return tableFontStrength;
	}
	public void setTableFontStrength(String tableFontStrength) {
		this.tableFontStrength = tableFontStrength;
	}
	public Float getBasicTextSize() {
		return basicTextSize;
	}
	public void setBasicTextSize(Float basicTextSize) {
		this.basicTextSize = basicTextSize;
	}
	public Float getTableTextSize() {
		return tableTextSize;
	}
	public void setTableTextSize(Float tableTextSize) {
		this.tableTextSize = tableTextSize;
	}
	public Float getHeadingTextSize() {
		return headingTextSize;
	}
	public void setHeadingTextSize(Float headingTextSize) {
		this.headingTextSize = headingTextSize;
	}
	public String getTableHeaders() {
		return tableHeaders;
	}
	public void setTableHeaders(String tableHeaders) {
		this.tableHeaders = tableHeaders;
	}
	public float[] getTableColsConfig() {
		return tableColsConfig;
	}
	public void setTableColsConfig(float[] tableColsConfig) {
		this.tableColsConfig = tableColsConfig;
	}
	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}
	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	public String getTableHeaderBckgrdColor() {
		return tableHeaderBckgrdColor;
	}
	public void setTableHeaderBckgrdColor(String tableHeaderBckgrdColor) {
		this.tableHeaderBckgrdColor = tableHeaderBckgrdColor;
	}
	public int getTableHeadingBorder() {
		return tableHeadingBorder;
	}
	public void setTableHeadingBorder(int tableHeadingBorder) {
		this.tableHeadingBorder = tableHeadingBorder;
	}
	public Float getTableWidthPercentage() {
		return tableWidthPercentage;
	}
	public void setTableWidthPercentage(Float tableWidthPercentage) {
		this.tableWidthPercentage = tableWidthPercentage;
	}
	public Float getTablePaddingTop() {
		return tablePaddingTop;
	}
	public void setTablePaddingTop(Float tablePaddingTop) {
		this.tablePaddingTop = tablePaddingTop;
	}
	public Float getTableBorder() {
		return tableBorder;
	}
	public void setTableBorder(Float tableBorder) {
		this.tableBorder = tableBorder;
	}
	public String getCellHorizontalAlignment() {
		return cellHorizontalAlignment;
	}
	public void setCellHorizontalAlignment(String cellHorizontalAlignment) {
		this.cellHorizontalAlignment = cellHorizontalAlignment;
	}
	public String getCellVerticalAlignment() {
		return cellVerticalAlignment;
	}
	public void setCellVerticalAlignment(String cellVerticalAlignment) {
		this.cellVerticalAlignment = cellVerticalAlignment;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [destPath=");
		builder.append(destPath);
		builder.append(", header=");
		builder.append(header);
		builder.append(", footer=");
		builder.append(footer);
		builder.append(", heightUnits=");
		builder.append(heightUnits);
		builder.append(", widthUnits=");
		builder.append(widthUnits);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", logo=");
		builder.append(logo);
		builder.append(", logoHeader=");
		builder.append(logoHeader);
		builder.append(", logoBorder=");
		builder.append(logoBorder);
		builder.append(", logoScalePercentage=");
		builder.append(logoScalePercentage);
		builder.append(", logoHorizontalAlignment=");
		builder.append(logoHorizontalAlignment);
		builder.append(", logoVerticalAlignment=");
		builder.append(logoVerticalAlignment);
		builder.append(", basicFont=");
		builder.append(basicFont);
		builder.append(", headingFont=");
		builder.append(headingFont);
		builder.append(", tableFont=");
		builder.append(tableFont);
		builder.append(", basicFontStrength=");
		builder.append(basicFontStrength);
		builder.append(", headingFontStrength=");
		builder.append(headingFontStrength);
		builder.append(", tableFontStrength=");
		builder.append(tableFontStrength);
		builder.append(", basicTextSize=");
		builder.append(basicTextSize);
		builder.append(", tableTextSize=");
		builder.append(tableTextSize);
		builder.append(", headingTextSize=");
		builder.append(headingTextSize);
		builder.append(", tableHeaders=");
		builder.append(tableHeaders);
		builder.append(", tableColsConfig=");
		builder.append(Arrays.toString(tableColsConfig));
		builder.append(", numberOfColumns=");
		builder.append(numberOfColumns);
		builder.append(", tableHeaderBckgrdColor=");
		builder.append(tableHeaderBckgrdColor);
		builder.append(", tableHeadingBorder=");
		builder.append(tableHeadingBorder);
		builder.append(", tableWidthPercentage=");
		builder.append(tableWidthPercentage);
		builder.append(", tablePaddingTop=");
		builder.append(tablePaddingTop);
		builder.append(", tableBorder=");
		builder.append(tableBorder);
		builder.append(", cellHorizontalAlignment=");
		builder.append(cellHorizontalAlignment);
		builder.append(", cellVerticalAlignment=");
		builder.append(cellVerticalAlignment);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}
}
