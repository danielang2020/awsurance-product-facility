package com.danielang.elastic.productfacility.domain;

import com.danielang.elastic.productfacility.db.entity.ProductPremiumSARateEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPrimaryKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:47
 **/
public final class ProductPremiumSARate implements Domain, DomainConverter<ProductPremiumSARateEntity> {
	private String calculationAge;
	private Map<String, String> productPremiumSARateIndicators;

	public ProductPremiumSARate(String calculationAge, Map<String, String> productPremiumSARateIndicators) {
		this.calculationAge = calculationAge;
		this.productPremiumSARateIndicators = new HashMap<>(productPremiumSARateIndicators);
	}

	public String getCalculationAge() {
		return calculationAge;
	}

	public Map<String, String> getProductPremiumSARateIndicators() {
		return new HashMap<>(productPremiumSARateIndicators);
	}

	@Override
	public ProductPremiumSARateEntity convert(Object... args) {
		ProductPrimaryKey pk = (ProductPrimaryKey) args[0];
		return new ProductPremiumSARateEntity(pk.insuranceTenant(), pk.productCategory(), pk.productCode(),
				pk.productType(), getCalculationAge(), getProductPremiumSARateIndicators());
	}
}
