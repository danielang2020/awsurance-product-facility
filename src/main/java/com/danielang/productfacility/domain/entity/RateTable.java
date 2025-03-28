package com.danielang.productfacility.domain.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-15 17:33
 **/
public class RateTable {
	private String name;
	private List<Rate> rates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public void validate() {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Rate Table Name is required");
		}

		if (rates == null || rates.isEmpty()) {
			throw new IllegalArgumentException("Rate Table Rates is required");
		}else{
			rates.forEach(Rate::validate);
		}
	}
}
