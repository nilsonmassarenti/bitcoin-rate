package com.nilsonmassarenti.app.bitcoinrate.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * HttpService is responsible for communicating between the application 
 * for HTTP requests to capture Bitcoin Rate and Bitcoin Rate Historical.
 * @author Nilson Massarenti
 * @version 1.0
 * @since Release 07-Oct-2019
 *
 */
public class HttpService {
	
	private final String USER_AGENT = "Mozilla/5.0";

	private static HttpService instance;

	private HttpService() {
	}

	public static synchronized HttpService getInstance() {
		if (instance == null) {
			instance = new HttpService();
		}
		return instance;
	}

	/**
	 * This method is responsible for get bitcoin rate
	 * @param currencyCode
	 * @return String - JSON
	 */
	public String getBitcoinRate(String currencyCode) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice/" + currencyCode + ".json");

		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() > 299) {
				return null;
			}

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			return result.toString();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * This method is responsible for get bitcoin rate historical
	 * @param currencyCode
	 * @param startDate
	 * @param endDate
	 * @return String - JSON
	 */
	public String getBitcoinRateByPeriod(String currencyCode, String startDate, String endDate) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://api.coindesk.com/v1/bpi/historical/close.json?currency=" + currencyCode
				+ "&start=" + startDate + "&end=" + endDate);

		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() > 299) {
				return null;
			}

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			return result.toString();
		} catch (IOException e) {
			return null;
		}

	}

}
