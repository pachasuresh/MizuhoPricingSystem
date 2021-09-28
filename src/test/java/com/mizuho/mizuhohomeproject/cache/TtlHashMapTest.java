package com.mizuho.mizuhohomeproject.cache;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class TtlHashMapTest {
	@Test
	public void get() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		String value = (String)ttlHashMap.get("AGS1234YD");
		assertThat(value,is(equalTo("AGS1234YD")));
	}
	@Test
	public void sholdGetValueExipre() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.NANOSECONDS,0);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		String value = (String)ttlHashMap.get("AGS1234YD");
		assertThat(value,is(equalTo(null)));
	}
	@Test
	public void containsKey() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		boolean value = ttlHashMap.containsKey("AGS1234YD");
		assertThat(value,is(equalTo(true)));
	}
	@Test
	public void containsValue() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		boolean value = ttlHashMap.containsValue("AGS1234YD");
		assertThat(value,is(equalTo(true)));
	}
	@Test
	public void values() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		Collection<String> value = ttlHashMap.values();
		assertThat(value.size(),is(equalTo(1)));
	}
	@Test
	public void keySet() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		Set<String> value = ttlHashMap.keySet();
		assertThat(value.size(),is(equalTo(1)));
	}
	@Test
	public void remove() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		Object value = ttlHashMap.remove("AGS1234YD");
		assertThat(value,is(equalTo("AGS1234YD")));
	}
	@Test
	public void putAll() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		TtlHashMap<String,String> ttlHashMapPutAll = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMapPutAll.putAll(ttlHashMap);
		assertThat(ttlHashMapPutAll.size(),is(equalTo(1)));
	}
	@Test
	public void clear() {
		TtlHashMap<String,String> ttlHashMap = new TtlHashMap<>(TimeUnit.DAYS,30);
		ttlHashMap.put("AGS1234YD", "AGS1234YD");
		ttlHashMap.clear();
		assertThat(ttlHashMap.size(),is(equalTo(0)));
	}

}
