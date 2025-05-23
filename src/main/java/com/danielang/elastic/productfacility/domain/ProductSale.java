package com.danielang.elastic.productfacility.domain;

import com.danielang.elastic.productfacility.db.entity.ProductPrimaryKey;
import com.danielang.elastic.productfacility.db.entity.ProductSaleEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:46
 **/
public final class ProductSale implements Domain, DomainConverter<ProductSaleEntity> {
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

	@Override
	public ProductSaleEntity convert(Object... args) {
		ProductPrimaryKey pk = (ProductPrimaryKey) args[0];
		return new ProductSaleEntity(pk.insuranceTenant(), pk.productCategory(), pk.productCode(), pk.productType(),
				getProductCurrency(), getProductStartDate(), getProductEndDate(), getProductSaleIndicators());
	}

}
