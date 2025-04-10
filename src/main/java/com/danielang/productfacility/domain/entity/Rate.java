package com.danielang.productfacility.domain.entity;

import java.math.BigDecimal;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-26 18:34
 **/
public class Rate {
	private final String format;
	private final BigDecimal value;

	public Rate(Rate rate) {
		this.format = rate.getFormat();
		this.value = rate.getValue();
	}

	public Rate(String format,BigDecimal value) {
		this.format = format;
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void validate() {
		if(format == null || format.isEmpty()) {
			throw new IllegalArgumentException("Rate format cannot be null or empty");
		}

		if(value == null) {
			throw new IllegalArgumentException("Rate value cannot be null");
		}
	}
}
