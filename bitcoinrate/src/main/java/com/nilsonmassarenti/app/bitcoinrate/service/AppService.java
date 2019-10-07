package com.nilsonmassarenti.app.bitcoinrate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPrice;
import com.nilsonmassarenti.app.bitcoinrate.entity.BitcoinPriceHistorical;

/**
 * AppService is the class of service responsible for and transformation and 
 * communication among other services.
 * @author Nilson Massarenti
 * @version 1.0
 * @since Release 07-Oct-2019
 */
public class AppService {

	private HttpService httpService;
	
	private static AppService instance;

	private AppService() {
		this.httpService = HttpService.getInstance();
	}

	public static synchronized AppService getInstance() {
		if (instance == null) {
			instance = new AppService();
		}
		return instance;
	}
	
	/**
	 * This method is responsible for getting the Bitcoin Rate.
	 * @param currencyCode
	 * @return BitcoinPrice
	 */
	public BitcoinPrice getBitcoinRate(String currencyCode) {
		String result = this.httpService.getBitcoinRate(currencyCode);
		if (result == null) {
			return null;
		}
		return convertStringToBitcoinPrice(result); 
	}
	
	/**
	 * This method is responsible for getting the Historical Bitcoin Rate.
	 * @param currencyCode
	 * @param startDate
	 * @param endDate
	 * @return BitcoinPriceHistorical
	 */
	public BitcoinPriceHistorical getBitcoinRateByPeriod(String currencyCode, String startDate, String endDate) {
		String result = this.httpService.getBitcoinRateByPeriod(currencyCode, startDate, endDate);
		if (result == null) {
			return null;
		}
		return convertStringToBitcoinPriceHistorical(result); 
	}
	
	/**
	 * This method is responsible for converting JSON to BitcoinPrice.
	 * @param bitcoinInfo
	 * @return BitcoinPrice
	 */
	private BitcoinPrice convertStringToBitcoinPrice(String bitcoinInfo) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
		BitcoinPrice bitcoinPrice = null;
		try {
			bitcoinPrice = mapper.readValue(bitcoinInfo, BitcoinPrice.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return bitcoinPrice;
	}
	
	/**
	 * This method is responsible for converting JSON to BitcoinPriceHistorical.
	 * @param bitcoinInfoHistorical
	 * @return
	 */
	private BitcoinPriceHistorical convertStringToBitcoinPriceHistorical(String bitcoinInfoHistorical) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
		BitcoinPriceHistorical bitcoinPriceHistorical = null;
		try {
			bitcoinPriceHistorical =  mapper.readValue(bitcoinInfoHistorical, BitcoinPriceHistorical.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return bitcoinPriceHistorical;
		
	}
	

}
