package com.danielang.elastic.productfacility.domain;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-23 23:18
 **/
public class ProductPKAndSK {
	private final String pk;
	private final String sk;

	public ProductPKAndSK(String pk, String sk) {
		this.pk = pk;
		this.sk = sk;
	}

	public String getPk() {
		return pk;
	}

	public String getSk() {
		return sk;
	}
}
