package com.nilsonmassarenti.app.bitcoinrate.entity.dto;

import java.math.BigDecimal;

public class BitcoinRateDTO {
	
	private BigDecimal current;
	private BigDecimal lowest;
	private BigDecimal highest;
	
	public BigDecimal getCurrent() {
		return current;
	}
	public void setCurrent(BigDecimal current) {
		this.current = current;
	}
	public BigDecimal getLowest() {
		return lowest;
	}
	public void setLowest(BigDecimal lowest) {
		this.lowest = lowest;
	}
	public BigDecimal getHighest() {
		return highest;
	}
	public void setHighest(BigDecimal highest) {
		this.highest = highest;
	}
	
	

}
