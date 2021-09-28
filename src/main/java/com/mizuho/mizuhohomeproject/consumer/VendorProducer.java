package com.mizuho.mizuhohomeproject.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.mizuho.mizuhohomeproject.cache.ClientPublisher;
import com.mizuho.mizuhohomeproject.entity.VendorRawPrice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VendorProducer {
	
	@Autowired
    private JmsTemplate jmsTemplate;	
	
	@Autowired
	ClientPublisher clientPublisher;
	
	public void sendMessage(VendorRawPrice price) {
	    log.debug("Sending prices to ESB Application.");	   
	    jmsTemplate.convertAndSend("price", price);
	    clientPublisher.publicToClient();
	}

}
