package com.danielang.elastic.productfacility.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:46
 **/
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
		this.productSaleIndicators = new HashMap<>(productSaleIndicators);
	}

	public String getProductCurrency() {
		return productCurrency;
	}

	public long getProductStartDate() {
		return productStartDate;
	}

	public long getProductEndDate() {
		return productEndDate;
	}

	public Map<String, String> getProductSaleIndicators() {
		return new HashMap<>(productSaleIndicators);
	}

}
