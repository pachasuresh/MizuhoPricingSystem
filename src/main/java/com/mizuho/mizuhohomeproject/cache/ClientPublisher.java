package com.mizuho.mizuhohomeproject.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientPublisher {	
	@Autowired
	TtlHashMap<String,VendorPrice> ttlHashMap;
	@Autowired
	JmsTemplate jmsTemplate;
	public void publicToClient() {	
		log.info("Sending prices to client Application.");
	    if(ttlHashMap != null)
	    log.info("cache size={}",ttlHashMap.size());
	    if(MapUtils.isNotEmpty(ttlHashMap)) {
	    	Set<Entry<String, VendorPrice>>	 entrySet = ttlHashMap.entrySet();
	    	for(Map.Entry<String, VendorPrice> entry : entrySet) {
	    		VendorPrice vendorPrice = ttlHashMap.get(entry.getKey());
	    		jmsTemplate.convertAndSend("clientPrice", vendorPrice);
	    	}
	    }	    
	}
}
