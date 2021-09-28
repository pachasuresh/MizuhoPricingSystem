package com.mizuho.mizuhohomeproject.util;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.entity.VendorRawPrice;

public class TransferRawDataToAcutalPrice {

	public static VendorPrice to(VendorRawPrice price) {
		VendorPrice vendorPrice = new VendorPrice();
		vendorPrice.setSecurityId(price.getSecurityId());
		vendorPrice.setSecurityDesc(price.getSecurityDesc());
		vendorPrice.setInventoryId(price.getInventoryId());
		vendorPrice.setPrice(price.getPrice());
		vendorPrice.setYeild(price.getYeild());
		vendorPrice.setSource(price.getSource());
		return vendorPrice;
	}
}
