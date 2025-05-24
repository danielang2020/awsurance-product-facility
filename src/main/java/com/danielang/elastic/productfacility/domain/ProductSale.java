package com.danielang.elastic.productfacility.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:46
 **/
@Data
public final class ProductSale implements Domain {
	private String productCurrency;
	private long productStartDate;
	private long productEndDate;
	private Map<String, String> productSaleIndicators;

	public ProductSale(String productCurrency, long productStartDate, long productEndDate,
			Map<String, String> productSaleIndicators) {
		this.productCurrency = productCurrency;
		this.productStartDate = productStartDate;
		this.productEndDate = productEndDate;
		this.productSaleIndicators =
				productSaleIndicators != null ? new HashMap<>(productSaleIndicators) : new HashMap<>(0);
	}


	public Map<String, String> getProductSaleIndicators() {
		return new HashMap<>(productSaleIndicators);
	}

}
