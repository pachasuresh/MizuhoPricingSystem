package com.mizuho.mizuhohomeproject.disruptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventHandler;
import com.mizuho.mizuhohomeproject.cache.TtlHashMap;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.repository.VendorPriceRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VendorPriceConsumer implements EventHandler<VendorPriceEvent> {

	@Autowired
	VendorPriceRepository vendorPriceRepository;
	
	@Autowired
	TtlHashMap<String,VendorPrice> cache;

	@Override
	public void onEvent(VendorPriceEvent event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		VendorPrice price = event.getVendorPrice();
		log.info("Price is recevied from disruptor={}",price.getYeild());
		vendorPriceRepository.save(price);
		cache.put(price.getSecurityId(), price);
		log.info("cache size={}",cache.size());
		
	}

}
