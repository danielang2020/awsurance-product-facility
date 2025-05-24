package com.danielang.elastic.productfacility.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:47
 **/
@Data
public final class ProductPremiumSARate implements Domain {
	private String calculationAge;
	private Map<String, String> productPremiumSARateIndicators;

	public ProductPremiumSARate(String calculationAge, Map<String, String> productPremiumSARateIndicators) {
		this.calculationAge = calculationAge;
		this.productPremiumSARateIndicators = productPremiumSARateIndicators != null ?
				new HashMap<>(productPremiumSARateIndicators) :
				new HashMap<>(0);
	}

	public Map<String, String> getProductPremiumSARateIndicators() {
		return new HashMap<>(productPremiumSARateIndicators);
	}

}
