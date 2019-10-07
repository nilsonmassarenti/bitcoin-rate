package com.nilsonmassarenti.app.bitcoinrate.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nilsonmassarenti.app.bitcoinrate.controller.AppController;
import com.nilsonmassarenti.app.bitcoinrate.entity.dto.BitcoinRateDTO;
import com.nilsonmassarenti.app.bitcoinrate.exception.CurrencyNotFoundException;

public class AppView {

	public static void readScreen(){
		AppController appController = AppController.getInstance();
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		try {
			System.out.print("Type currency code and press <<Enter>>: ");
			String currencyCode = reader.readLine();
			BitcoinRateDTO bitcoinRateDTO = appController.processBitcoinRateRequest(currencyCode);
			System.out.println("The current Bitcoin rate: " + bitcoinRateDTO.getCurrent().toString());
			System.out.println("The lowest Bitcoin rate in the last 30 days: " + bitcoinRateDTO.getLowest().toString());
			System.out.println("The highest Bitcoin rate in the last 30 days: " + bitcoinRateDTO.getHighest().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CurrencyNotFoundException e) {
			System.err.append(e.getMessage());
		} 
	}
}
