package com.mizuho.mizuhohomeproject.entity;

public class VendorRawPrice {

	private String securityId;
	private Double price;
	private Double yeild;
	private String source;
	private String securityDesc;
	private long inventoryId;

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getYeild() {
		return yeild;
	}

	public void setYeild(Double yeild) {
		this.yeild = yeild;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSecurityDesc() {
		return securityDesc;
	}

	public void setSecurityDesc(String securityDesc) {
		this.securityDesc = securityDesc;
	}

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

}
