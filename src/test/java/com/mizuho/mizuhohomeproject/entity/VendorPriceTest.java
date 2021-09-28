package com.mizuho.mizuhohomeproject.entity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class VendorPriceTest {

	@Test
	public void shouldToString() {
	  VendorPrice vendorPriceObj = new VendorPrice("AGS1234YD","Muni Bond",1063L,120.3,100.3,"IDC");
	  String value = vendorPriceObj.toString();
	  assertThat(value,is(equalTo("VendorPrice [price=120.3, yeild=100.3, source=IDC, securityId=AGS1234YD, securityDesc=Muni Bond, inventoryId=1063]")));
	}
}
