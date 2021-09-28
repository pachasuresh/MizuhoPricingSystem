package com.mizuho.mizuhohomeproject.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mizuho.mizuhohomeproject.cache.TtlHashMap;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;

@Service
public class UpdatePricesService {
	
	@Autowired
	TtlHashMap<String,VendorPrice> ttlHashMap;
	
	//getting a specific record by using the method findById() of CrudRepository  
	public VendorPrice priceBySecurityId(String id){
		return ttlHashMap.get(id);  
	}
	
	public Set<VendorPrice> getAllPrices(){	
		Set<VendorPrice> vendorPrices = new HashSet<VendorPrice>();
	    if(MapUtils.isNotEmpty(ttlHashMap)) {
	    	Set<Entry<String, VendorPrice>>	 entrySet = ttlHashMap.entrySet();
	    	for(Map.Entry<String, VendorPrice> entry : entrySet) {
	    		VendorPrice vendorPrice = ttlHashMap.get(entry.getKey());
	    		vendorPrices.add(vendorPrice);
	    	}
	    }	
		return vendorPrices;
	}  
	public VendorPrice updatePriceBySecurityId(VendorPrice vendorPrice) {
		 if(vendorPrice != null && MapUtils.isNotEmpty(ttlHashMap)) {
			  VendorPrice price = ttlHashMap.get(vendorPrice.getSecurityId());
			  if(price != null) {
				  price.setInventoryId(vendorPrice.getInventoryId());
				  price.setPrice(vendorPrice.getPrice());
				  price.setSecurityDesc(vendorPrice.getSecurityDesc());
				  price.setYeild(vendorPrice.getYeild());
				  price.setSource(vendorPrice.getSource());
				  ttlHashMap.put(vendorPrice.getSecurityId(), price);
			  }
		    }
		 return ttlHashMap.get(vendorPrice.getSecurityId());
	}

	public Set<String> updateAllPrices(Set<VendorPrice> vendorPrices) {
		for (VendorPrice vendorPrice : vendorPrices) {
			if (vendorPrice != null && MapUtils.isNotEmpty(ttlHashMap)) {
				Set<String> entrykeySet = ttlHashMap.keySet();
				for (String key : entrykeySet) {
					VendorPrice price = ttlHashMap.get(key);
					if (price != null) {
						price.setInventoryId(vendorPrice.getInventoryId());
						price.setPrice(vendorPrice.getPrice());
						price.setSecurityDesc(vendorPrice.getSecurityDesc());
						price.setYeild(vendorPrice.getYeild());
						price.setSource(vendorPrice.getSource());
						ttlHashMap.put(key, price);
					}
				}

			}
		}
		return ttlHashMap.keySet();
	}

}
