package com.mizuho.mizuhohomeproject;

import java.util.concurrent.TimeUnit;

import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.mizuho.mizuhohomeproject.cache.ClientPublisher;
import com.mizuho.mizuhohomeproject.cache.TtlHashMap;
import com.mizuho.mizuhohomeproject.consumer.VendorProducer;
import com.mizuho.mizuhohomeproject.disruptor.EventDisruptorService;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;
import com.mizuho.mizuhohomeproject.entity.VendorRawPrice;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJms
@Slf4j
public class MizuhoHomeProjectApplication implements CommandLineRunner {
	
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	VendorProducer vendorProducer;
	@Autowired
	EventDisruptorService eventDisruptorService;
	
	@Autowired
	ClientPublisher clientPublisher;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MizuhoHomeProjectApplication.class, args);		
	}
	@Bean
	public TtlHashMap<String,VendorPrice> getTtlHashMap(){
		return new TtlHashMap<>(TimeUnit.DAYS,30); 
	};
	
	 @Bean
	  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    // This provides all boot's default to this factory, including the message converter
	    configurer.configure(factory, connectionFactory);
	    // You could still override some of Boot's default if necessary.
	    return factory;
	  }
	 @Bean // Serialize message content to json using TextMessage
	  public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	  }
	 @Bean
	  public JmsListenerContainerFactory<?> clientJmsFactory(ConnectionFactory connectionFactory,
	                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setPubSubDomain(true);
	    // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	    // You could still override some of Boot's default if necessary.
	    return factory;
	  }	 
	 
	@Override
	public void run(String... args) throws Exception {
		eventDisruptorService.initializeDisruptor();
		VendorRawPrice price = new VendorRawPrice();
		price.setSecurityId("AGS1234YD");
		price.setSecurityDesc("Muni Bond");
		price.setInventoryId(1063L);
	    price.setPrice(120.12);
	    price.setYeild(65.1);
	    price.setSource("IDC");
	    vendorProducer.sendMessage(price);
		Thread.sleep(5000);
		clientPublisher.publicToClient();
	}
	
}
