package com.danielang.elastic.productfacility.domain;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:44
 **/
public final class ProductInformation implements Domain  {
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

}
