package com.danielang.elastic.productfacility.domain;

import lombok.Data;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-19 16:44
 **/
@Data
public final class ProductInformation implements Domain {
	private String productName;
	private String productAbbrevName;
	private String productDescription;
}
