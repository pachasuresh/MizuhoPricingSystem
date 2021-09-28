package com.mizuho.mizuhohomeproject.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mizuho.mizuhohomeproject.cache.TtlHashMap;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.services.UpdatePricesService;

@ExtendWith(MockitoExtension.class)
public class UpdatePricesControllerTest {
	
	@InjectMocks
	UpdatePricesController updatePricesController;
	
	@Mock
	UpdatePricesService updatePricesService;
	
	@Mock
	TtlHashMap<String,VendorPrice> ttlHashMap;
	
	@Test
	public void findPriceBySecurityId() {
	 VendorPrice vendorPrice = Mockito.mock(VendorPrice.class);	
	 when(updatePricesController.findPriceBySecurityId("AGS1234YD")).thenReturn(vendorPrice);
	 VendorPrice returnVendorPrice = updatePricesController.findPriceBySecurityId("AGS1234YD");	 
	 //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
	 assertThat(vendorPrice.getInventoryId(),is(equalTo(returnVendorPrice.getInventoryId())));
	}
	
	@Test
	public void getAllPrices() {
	 Set<VendorPrice> vendorPrices = Mockito.mock(Set.class);
	 when(updatePricesController.getAllPrices()).thenReturn(vendorPrices);
	 Set<VendorPrice> returnVendorPrices = updatePricesController.getAllPrices();	 
	 //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
	 assertThat(vendorPrices.size(),is(equalTo(returnVendorPrices.size())));
	}
	
	@Test
	public void updatePriceBySecurityId() {
	 VendorPrice vendorPrice = Mockito.mock(VendorPrice.class);	
	 when(updatePricesController.updatePriceBySecurityId(any())).thenReturn(vendorPrice);
	 VendorPrice returnVendorPrice = updatePricesController.updatePriceBySecurityId(any());	 
	 //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
	 assertThat(vendorPrice.getInventoryId(),is(equalTo(returnVendorPrice.getInventoryId())));
	}
	
	@Test
	public void updateAllPrices() {
	 Set<String> vendorPrices = Mockito.mock(Set.class);
	 when(updatePricesController.updateAllPrices(anySet())).thenReturn(vendorPrices);
	 Set<String> returnVendorPrices = updatePricesController.updateAllPrices(anySet());	 
	 //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
	 assertThat(vendorPrices.size(),is(equalTo(returnVendorPrices.size())));
	}

	
}
