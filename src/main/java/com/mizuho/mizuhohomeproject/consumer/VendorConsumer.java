package com.mizuho.mizuhohomeproject.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.RingBuffer;
import com.mizuho.mizuhohomeproject.disruptor.EventDisruptorService;
import com.mizuho.mizuhohomeproject.disruptor.VendorPriceEvent;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.entity.VendorRawPrice;
import com.mizuho.mizuhohomeproject.util.TransferRawDataToAcutalPrice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VendorConsumer {
	
	@Autowired
	private EventDisruptorService eventDisruptorService;
	
	@JmsListener(destination = "price", containerFactory = "myFactory")
	  public void onMessage(VendorRawPrice price) {
		
		VendorPrice vendorPrice = TransferRawDataToAcutalPrice.to(price);		
		RingBuffer<VendorPriceEvent> vendorPriceRingBuffer = 
	    		eventDisruptorService.getVendroPriceRingBuffer();
	    long seq = vendorPriceRingBuffer.next();
	    VendorPriceEvent event = vendorPriceRingBuffer.get(seq);
	    event.setVendorPrice(vendorPrice);
	    vendorPriceRingBuffer.publish(seq);   
	    log.debug("Event is publish to RingBuffer");
	    
	  }

}
