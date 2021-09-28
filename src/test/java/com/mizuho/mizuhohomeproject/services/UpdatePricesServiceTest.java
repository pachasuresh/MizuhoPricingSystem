package com.mizuho.mizuhohomeproject.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mizuho.mizuhohomeproject.cache.TtlHashMap;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;

@ExtendWith(MockitoExtension.class)
public class UpdatePricesServiceTest {
	
	@InjectMocks
	UpdatePricesService updatePricesService;

	@Mock
	TtlHashMap<String,VendorPrice> ttlHashMap;
	
	@Test
	public void priceBySecurityId() {
	  VendorPrice vendorPrice = Mockito.mock(VendorPrice.class);
	  when(ttlHashMap.size()).thenReturn(5);
	  when(updatePricesService.priceBySecurityId("AGS1234YD")).thenReturn(vendorPrice);	 
	  VendorPrice price = updatePricesService.priceBySecurityId("AGS1234YD");
	  assertThat(ttlHashMap.size(),is(equalTo(5)));		
	  assertThat(vendorPrice.getInventoryId(),is(equalTo(price.getInventoryId())));
	}
	
	@Test
	public void getAllPrices() {		
	  Set<VendorPrice> vendorPrices = new HashSet<>();
	  VendorPrice vendorPriceObj = new VendorPrice();	
	  vendorPriceObj.setSecurityId("AGS1234YD");
      Set<Map.Entry<String,VendorPrice>> entrySet = new HashSet<Map.Entry<String,VendorPrice>>();  
      Map.Entry<String,VendorPrice> entry = new SimpleEntry<String, VendorPrice>("AGS1234YD",vendorPriceObj);
      entrySet.add(entry);
      //when(updatePricesService.updateAllPrices(vendorPrices)).thenReturn(vendorPricekeys);
      when(ttlHashMap.entrySet()).thenReturn(entrySet);
      when(ttlHashMap.get(vendorPriceObj.getSecurityId())).thenReturn(vendorPriceObj);
      vendorPrices = updatePricesService.getAllPrices();	  
	  //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
	  assertThat(vendorPrices.size(),is(equalTo(1)));
	}
	@Test
	public void updatePriceBySecurityId() {
		VendorPrice vendorPrice = Mockito.mock(VendorPrice.class);
		when(updatePricesService.updatePriceBySecurityId(vendorPrice)).thenReturn(vendorPrice);	 
		VendorPrice price = updatePricesService.updatePriceBySecurityId(vendorPrice);	
		assertThat(vendorPrice.getInventoryId(),is(equalTo(price.getInventoryId())));
	}
	
	  @Test 
	  public void updateAllPrices() { 
		  Set<VendorPrice> vendorPrices = new HashSet<>();
		  VendorPrice vendorPriceObj = new VendorPrice("AGS1234YD","Muni Bond",1063L,120.3,100.3,"IDC");	
		  vendorPriceObj.setSecurityId("AGS1234YD");		  
		  vendorPrices.add(vendorPriceObj);
	      Set<String> vendorPricekeys = new HashSet<String>();
	      vendorPricekeys.add(vendorPriceObj.getSecurityId());
		  //when(updatePricesService.updateAllPrices(vendorPrices)).thenReturn(vendorPricekeys);
	      when(ttlHashMap.keySet()).thenReturn(vendorPricekeys);
	      when(ttlHashMap.get(vendorPriceObj.getSecurityId())).thenReturn(vendorPriceObj);
	      vendorPricekeys = updatePricesService.updateAllPrices(vendorPrices);
		  
		  //assertThat(closeTo(vendorPrice.getPrice(),returnVendorPrice.getPrice()));	 
		  assertThat(vendorPricekeys.size(),is(equalTo(1)));
	  
	 }
	 
}
