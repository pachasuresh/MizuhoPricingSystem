package com.mizuho.mizuhohomeproject.cache;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.mizuho.mizuhohomeproject.entity.VendorPrice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientConsumer {
	
	@JmsListener(destination = "clientPrice", containerFactory = "clientJmsFactory")
	  public void onMessage(VendorPrice price) {
		log.info("client side price={}",price.getPrice());
	  }

}
