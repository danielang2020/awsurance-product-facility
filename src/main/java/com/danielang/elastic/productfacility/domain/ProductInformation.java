package com.danielang.elastic.productfacility.domain;

import com.danielang.elastic.productfacility.db.entity.ProductInformationEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPrimaryKey;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:44
 **/
public final class ProductInformation implements Domain, DomainConverter<ProductInformationEntity> {
	private String productName;
	private String productAbbrevName;
	private String productDescription;

	public ProductInformation(String productName, String productAbbrevName, String productDescription) {
		this.productName = productName;
		this.productAbbrevName = productAbbrevName;
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductAbbrevName() {
		return productAbbrevName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	@Override
	public ProductInformationEntity convert(Object... args) {
		ProductPrimaryKey pk = (ProductPrimaryKey) args[0];
		return new ProductInformationEntity(pk.insuranceTenant(), pk.productCategory(), pk.productCode(),
				pk.productType(), getProductName(), getProductAbbrevName(), getProductDescription());
	}
}
