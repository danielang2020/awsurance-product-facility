package com.danielang.elastic.productfacility.db.entity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 23:36
 **/
public sealed interface DynamoDBEntity
		permits ProductEntity, ProductInformationEntity, ProductPremiumSARateEntity, ProductSaleEntity {
}
