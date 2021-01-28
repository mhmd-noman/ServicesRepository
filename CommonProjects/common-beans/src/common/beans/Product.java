package common.beans;

import java.util.Date;

public class Product {
	private Integer id = null;
	private String name = null;
	private String company = null;
	private String category = null;
	private String flavour = null;
	private Integer quantity = null;
	private Integer orderedQuantity = null;
	private String weight = null;
	private String servings = null;
	private String servingSize = null;
	private Double rtlPrice = null;
	private Double orgPrice = null;
	private Double fromOrgPrice = null;
	private Double toOrgPrice = null;
	private Double netPrice = null;
	private Double discount = null;
	private Double fromDiscount = null;
	private Double toDiscount = null;
	private Double discountWhenOrdered = null;
	private String directiontoUse = null;
	private String description = null;
	private String barCode = null;
	private Date mfgDate = null;
	private Date expiryDate = null;
	private Date createdOn = null;
	private Date lastUpdatedOn = null;
	private String isActive = null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFlavour() {
		return flavour;
	}
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getServings() {
		return servings;
	}
	public void setServings(String servings) {
		this.servings = servings;
	}
	public String getServingSize() {
		return servingSize;
	}
	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}
	public Double getRtlPrice() {
		return rtlPrice;
	}
	public void setRtlPrice(Double rtlPrice) {
		this.rtlPrice = rtlPrice;
	}
	public Double getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(Double orgPrice) {
		this.orgPrice = orgPrice;
	}
	public Double getFromOrgPrice() {
		return fromOrgPrice;
	}
	public void setFromOrgPrice(Double fromOrgPrice) {
		this.fromOrgPrice = fromOrgPrice;
	}
	public Double getToOrgPrice() {
		return toOrgPrice;
	}
	public void setToOrgPrice(Double toOrgPrice) {
		this.toOrgPrice = toOrgPrice;
	}
	public Double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getFromDiscount() {
		return fromDiscount;
	}
	public void setFromDiscount(Double fromDiscount) {
		this.fromDiscount = fromDiscount;
	}
	public Double getToDiscount() {
		return toDiscount;
	}
	public void setToDiscount(Double toDiscount) {
		this.toDiscount = toDiscount;
	}
	public Double getDiscountWhenOrdered() {
		return discountWhenOrdered;
	}
	public void setDiscountWhenOrdered(Double discountWhenOrdered) {
		this.discountWhenOrdered = discountWhenOrdered;
	}
	public String getDirectiontoUse() {
		return directiontoUse;
	}
	public void setDirectiontoUse(String directiontoUse) {
		this.directiontoUse = directiontoUse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public String isActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", company=");
		builder.append(company);
		builder.append(", category=");
		builder.append(category);
		builder.append(", flavour=");
		builder.append(flavour);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", orderedQuantity=");
		builder.append(orderedQuantity);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", servings=");
		builder.append(servings);
		builder.append(", servingSize=");
		builder.append(servingSize);
		builder.append(", rtlPrice=");
		builder.append(rtlPrice);
		builder.append(", orgPrice=");
		builder.append(orgPrice);
		builder.append(", fromOrgPrice=");
		builder.append(fromOrgPrice);
		builder.append(", toOrgPrice=");
		builder.append(toOrgPrice);
		builder.append(", netPrice=");
		builder.append(netPrice);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", fromDiscount=");
		builder.append(fromDiscount);
		builder.append(", toDiscount=");
		builder.append(toDiscount);
		builder.append(", discountWhenOrdered=");
		builder.append(discountWhenOrdered);
		builder.append(", directiontoUse=");
		builder.append(directiontoUse);
		builder.append(", description=");
		builder.append(description);
		builder.append(", barCode=");
		builder.append(barCode);
		builder.append(", mfgDate=");
		builder.append(mfgDate);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", lastUpdatedOn=");
		builder.append(lastUpdatedOn);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}
}
