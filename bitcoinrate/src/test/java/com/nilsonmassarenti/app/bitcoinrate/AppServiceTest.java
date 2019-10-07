package com.nilsonmassarenti.app.bitcoinrate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPrice;
import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPriceHistorical;
import com.nilsonmassarenti.app.bitcoinrate.service.AppService;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppServiceTest extends TestCase {

	@Test
	public void test01CheckValidCurrency() {
		AppService appService = AppService.getInstance();
		BitcoinPrice bitcoinPrice = appService.getBitcoinRate("USD");
		assertNotNull(bitcoinPrice);
	}
	
	@Test
	public void test02CheckValidCurrencyWithError() {
		AppService appService = AppService.getInstance();
		BitcoinPrice bitcoinPrice = appService.getBitcoinRate("XYZZZ");
		assertNull(bitcoinPrice);
	}
	
	public void test03BitcoinPriceHistorical() {
		AppService appService = AppService.getInstance();
		BitcoinPriceHistorical bitcoinPriceHistorical = appService.getBitcoinRateByPeriod("USD", "2019-09-07", "2019-10-07");
		assertNotNull(bitcoinPriceHistorical);
	}
	
	public void test03BitcoinPriceHistoricalWithError() {
		AppService appService = AppService.getInstance();
		BitcoinPriceHistorical bitcoinPriceHistorical = appService.getBitcoinRateByPeriod("XYZZZ", "2019-09-07", "2019-10-07");
		assertNull(bitcoinPriceHistorical);
	}


}