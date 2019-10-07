package com.nilsonmassarenti.app.bitcoinrate.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
	private String code;
	@JsonProperty("rate")
	private String rateFormat;
	@JsonProperty("rate_float")
	private BigDecimal rate;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRateFormat() {
		return rateFormat;
	}
	public void setRateFormat(String rateFormat) {
		this.rateFormat = rateFormat;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
