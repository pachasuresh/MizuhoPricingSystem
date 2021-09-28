package com.mizuho.mizuhohomeproject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.services.UpdatePricesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UpdatePricesController {
	
	@Autowired
	UpdatePricesService updatePricesService;
	
	@GetMapping("/price/{id}")
    public VendorPrice findPriceBySecurityId(@PathVariable String id) 
    {
        log.debug("Searching by ID  : " + id);
 
        log.debug("updatePricesService  : " + updatePricesService);
        
        return updatePricesService.priceBySecurityId(id);
    }
	
	@GetMapping("/prices")
    public Set<VendorPrice> getAllPrices() 
    {
		log.debug("getAll prices");
 
        return updatePricesService.getAllPrices();
    }
	
	@PostMapping("/price")
    public VendorPrice updatePriceBySecurityId(@RequestBody VendorPrice price) 
    {
		log.debug("updatePriceBySecurityId");
 
        return updatePricesService.updatePriceBySecurityId(price);
    }
	
	@PutMapping("/prices")  
	public Set<String> updateAllPrices(@RequestBody Set<VendorPrice> prices)   
	{  
		
		return updatePricesService.updateAllPrices(prices);   
	}  
	

}
