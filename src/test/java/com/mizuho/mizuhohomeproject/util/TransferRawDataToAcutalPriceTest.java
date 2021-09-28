package com.mizuho.mizuhohomeproject.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.entity.VendorRawPrice;

public class TransferRawDataToAcutalPriceTest {
	
	@Test
	public void transferRawDataToAcutalPrice() {
		TransferRawDataToAcutalPrice tranderRawData = new TransferRawDataToAcutalPrice();
		VendorRawPrice price = new VendorRawPrice();
		price.setSecurityId("AGS1234YD");
		price.setSecurityDesc("Muni Bond");
		price.setInventoryId(1063L);
	    price.setPrice(120.12);
	    price.setYeild(65.1);
	    price.setSource("IDC");
		VendorPrice vendorPrice = tranderRawData.to(price);
		assertThat(vendorPrice.getInventoryId(),is(equalTo(price.getInventoryId())));
	}

}
