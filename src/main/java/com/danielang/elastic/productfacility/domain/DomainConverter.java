package com.danielang.elastic.productfacility.domain;

import com.danielang.elastic.productfacility.db.entity.DynamoDBEntity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 22:45
 **/
public sealed interface DomainConverter<T extends DynamoDBEntity>
		permits Product, ProductInformation, ProductPremiumSARate, ProductSale {
	T convert(Object... args);
}
