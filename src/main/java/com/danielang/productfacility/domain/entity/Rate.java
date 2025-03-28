package com.danielang.productfacility.domain.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-26 18:34
 **/
public class Rate {
	private List<RateFactor> rateFactors;

	public List<RateFactor> getRateFactors() {
		return rateFactors;
	}

	public void setRateFactors(List<RateFactor> rateFactors) {
		this.rateFactors = rateFactors;
	}

	public void validate() {
		if (rateFactors == null || rateFactors.isEmpty()) {
			throw new IllegalArgumentException("Rate Factors is required");
		}else{
			rateFactors.forEach(RateFactor::validate);
		}
	}
}
