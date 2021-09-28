package com.mizuho.mizuhohomeproject.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import lombok.extern.slf4j.Slf4j;

@Component
@SuppressWarnings("deprecation")
@Slf4j
public class EventDisruptorService {

	private Disruptor<VendorPriceEvent> vendorPriceDisruptor = null;
	private ExecutorService vendorPriceExecutorService;
	private int ringBufferSize = 1024 * 8;
	private int numOfThreads = 4;
	private RingBuffer<VendorPriceEvent> vendorPriceRingBuffer;
	@Autowired
	private VendorPriceConsumer vendorPriceConsumer;

	@SuppressWarnings("unchecked")
	public void initializeDisruptor() {

		int size = 0;
		if (size == 0) {
			size = ringBufferSize;
		}
		vendorPriceExecutorService = Executors.newFixedThreadPool(numOfThreads);
		vendorPriceDisruptor = new Disruptor<VendorPriceEvent>(VendorPriceEvent.EVENT_FACTORY, size,
				vendorPriceExecutorService, ProducerType.MULTI, new BlockingWaitStrategy());
		vendorPriceDisruptor.handleEventsWith(vendorPriceConsumer);
		vendorPriceRingBuffer = vendorPriceDisruptor.start();
		log.info("Disruptor is started");
	}

	public RingBuffer<VendorPriceEvent> getVendroPriceRingBuffer() {
		return vendorPriceRingBuffer;
	}

}
