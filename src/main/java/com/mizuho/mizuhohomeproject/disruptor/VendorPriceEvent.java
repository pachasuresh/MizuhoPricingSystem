package com.mizuho.mizuhohomeproject.disruptor;

import com.lmax.disruptor.EventFactory;
import com.mizuho.mizuhohomeproject.entity.VendorPrice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VendorPriceEvent {

	private VendorPrice vendorPrice;

	public final static EventFactory<VendorPriceEvent> EVENT_FACTORY = new EventFactory<VendorPriceEvent>() {
		@Override
		public VendorPriceEvent newInstance() {
			return new VendorPriceEvent();
		}
	};

	public VendorPrice getVendorPrice() {
		return vendorPrice;
	}

	public void setVendorPrice(VendorPrice vendorPrice) {
		log.debug("Event is set");
		this.vendorPrice = vendorPrice;
	}

}
