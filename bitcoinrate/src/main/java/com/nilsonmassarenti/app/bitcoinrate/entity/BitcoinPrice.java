package com.nilsonmassarenti.app.bitcoinrate.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BitcoinPrice {
	
	@JsonIgnore
	private String time;
	private String disclaimer;
	private Map<String, Currency> bpi;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public Map<String, Currency> getBpi() {
		return bpi;
	}
	public void setBpi(Map<String, Currency> bpi) {
		this.bpi = bpi;
	}
	
	
}
