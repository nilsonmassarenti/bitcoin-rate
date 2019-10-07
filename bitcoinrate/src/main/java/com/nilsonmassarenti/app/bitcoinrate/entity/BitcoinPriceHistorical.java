package com.nilsonmassarenti.app.bitcoinrate.entity;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BitcoinPriceHistorical {
	
	private Map<String, BigDecimal> bpi;
	private String disclaimer;
	@JsonIgnore
	private String time;
	
	public Map<String, BigDecimal> getBpi() {
		return bpi;
	}
	public void setBpi(Map<String, BigDecimal> bpi) {
		this.bpi = bpi;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
