package com.nilsonmassarenti.app.bitcoinrate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.nilsonmassarenti.app.bitcoinrate.service.HttpService;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpServiceTest extends TestCase {
	
	@Test
	public void test01HttpGetCurrencyCode () throws Exception {
		HttpService httpService = HttpService.getInstance();
		String result = httpService.getBitcoinRate("USD");
		assertNotNull(result);
	}
	
	@Test
	public void test02HttpGetCurrencyCodeWithError () throws Exception {
		HttpService httpService = HttpService.getInstance();
		String result = httpService.getBitcoinRate("123");
		assertNull(result);
	}
	
	@Test
	public void test03HttpGetHistoricalCurrencyByPeriod() throws Exception {
		HttpService httpService = HttpService.getInstance();
		String result = httpService.getBitcoinRateByPeriod("USD", "2019-09-07" , "2019-10-07");
		assertNotNull(result);
	}
	
	@Test
	public void test04HttpGetHistoricalCurrencyByPeriodWithError() throws Exception {
		HttpService httpService = HttpService.getInstance();
		String result = httpService.getBitcoinRateByPeriod("USD", "XYZ" , "2019-10-07");
		assertNull(result);
	}
	
	

}
