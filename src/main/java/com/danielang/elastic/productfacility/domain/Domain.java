package com.danielang.elastic.productfacility.domain;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 17:06
 **/
public sealed interface Domain permits Product, ProductInformation, ProductPremiumSARate, ProductSale {
	default void validate(){}
}
