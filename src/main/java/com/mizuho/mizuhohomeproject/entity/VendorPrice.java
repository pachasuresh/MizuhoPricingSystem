package com.mizuho.mizuhohomeproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VendorPrices")
public class VendorPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "price")
	private Double price;
	@Column(name = "yeild")
	private Double yeild;
	@Column(name = "source")
	private String source;

	@Column(name = "securityId")
	private String securityId;

	@Column(name = "securityDesc")
	private String securityDesc;

	@Column(name = "inventoryId")
	private long inventoryId;

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

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
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

	public VendorPrice() {

	}

	public VendorPrice(String securityId, String securityDesc, long inventoryId, Double price, Double yeild,
			String source) {
		this.securityId = securityId;
		this.securityDesc = securityDesc;
		this.inventoryId = inventoryId;
		this.price = price;
		this.yeild = yeild;
		this.source = source;
	}

	@Override
	public String toString() {
		return "VendorPrice [price=" + price + ", yeild=" + yeild + ", source=" + source + ", securityId=" + securityId
				+ ", securityDesc=" + securityDesc + ", inventoryId=" + inventoryId + "]";
	}

}
