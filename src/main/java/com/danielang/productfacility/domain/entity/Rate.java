package com.danielang.productfacility.domain.entity;

import com.danielang.productfacility.domain.util.DomainUtil;

import java.math.BigDecimal;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-26 18:34
 **/
public class Rate {
	private final String rateFormat;
	private final BigDecimal rateValue;

	public Rate(Rate rate) {
		this.rateFormat = rate.getFormat();
		this.rateValue = rate.getValue();
	}

	public Rate(String rateFormat,BigDecimal rateValue) {
		this.rateFormat = rateFormat;
		this.rateValue = rateValue;
	}

	public String getFormat() {
		return rateFormat;
	}

	public BigDecimal getValue() {
		return rateValue;
	}

	public void validate() {
		DomainUtil.isNullOrBlank(rateFormat, "rateFormat");
		DomainUtil.isNullOrBlank(rateValue, "rateValue");
	}
}
