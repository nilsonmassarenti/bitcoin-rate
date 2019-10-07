package com.nilsonmassarenti.app.bitcoinrate.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPrice;
import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPriceHistorical;
import com.nilsonmassarenti.app.bitcoinrate.entity.Currency;
import com.nilsonmassarenti.app.bitcoinrate.entity.dto.BitcoinRateDTO;
import com.nilsonmassarenti.app.bitcoinrate.enums.EnumMessage;
import com.nilsonmassarenti.app.bitcoinrate.exception.CurrencyNotFoundException;
import com.nilsonmassarenti.app.bitcoinrate.service.AppService;

/**
 * AppController is responsible for managing requests for bitcoin information.
 * @author Nilson Massarenti
 * @version 1.0
 * @since Release 07-Oct-2019
 *
 */
public class AppController {
	
	private AppService appService;
	
	private static AppController instance;
	
	private AppController() {
		this.appService = AppService.getInstance();
	}
	
	public static synchronized AppController getInstance() {
		if(instance == null){
			instance = new AppController();
		}
		return instance;
	}
	
	/**
	 * This method is responsible for processing Bitcoin Rate, 
	 * Lowest and Highest Rate information on the last 30 days.
	 * @param currencyCode
	 * @return BitcoinRateDTO
	 * @throws CurrencyNotFoundException
	 */
	public BitcoinRateDTO processBitcoinRateRequest(String currencyCode) throws CurrencyNotFoundException {
		BitcoinPrice bitcoinPrice = this.appService.getBitcoinRate(currencyCode);
		if (bitcoinPrice == null) {
			throw new CurrencyNotFoundException(EnumMessage.CURRENCY_NOT_FOUND.message);
		}
		currencyCode = currencyCode.toUpperCase();
		String startDate = formatDate(LocalDate.now().minusDays(30l));
		String endDate = formatDate(LocalDate.now());
		
		BitcoinPriceHistorical bitcoinPriceHistorical = this.appService.getBitcoinRateByPeriod(currencyCode, startDate, endDate);
		List<BigDecimal> bpis = sortBitcoinRateHistorical(bitcoinPriceHistorical.getBpi());
		
		
		BitcoinRateDTO bitcoinRateDTO = new BitcoinRateDTO();
		bitcoinRateDTO.setCurrent(getCurrentRate(currencyCode, bitcoinPrice.getBpi()));
		bitcoinRateDTO.setLowest(bpis.get(0).setScale(4));
		bitcoinRateDTO.setHighest(bpis.get(bpis.size()-1).setScale(4));
		return bitcoinRateDTO;

	}
	
	/**
	 * 
	 * @param Map<String, BigDecimal>
	 * @return List<BigDecimal>
	 */
	private List<BigDecimal> sortBitcoinRateHistorical(Map<String, BigDecimal> bpi) {
		List<BigDecimal> result = new ArrayList<BigDecimal>(bpi.values());
		Collections.sort(result);
		return result;
	}
	
	/**
	 * 
	 * @param currencyCode, Map<String, Currency>
	 * @param bpi
	 * @return BigDecimal
	 */
	private BigDecimal getCurrentRate(String currencyCode, Map<String, Currency> bpi) {
		BigDecimal rate = bpi.get(currencyCode).getRate();
		rate.setScale(4);
		return rate.setScale(4);
	}
	
	/**
	 * 
	 * @param date
	 * @return String date formatted
	 */
	private String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}

}
