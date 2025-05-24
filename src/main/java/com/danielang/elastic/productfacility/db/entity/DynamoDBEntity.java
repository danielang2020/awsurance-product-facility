package com.danielang.elastic.productfacility.db.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 23:36
 **/
public abstract sealed class DynamoDBEntity
		permits ProductEntity, ProductInformationEntity, ProductPremiumSARateEntity, ProductSaleEntity {
	private String pk;
	private String sk;

	@DynamoDbPartitionKey
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	@DynamoDbSortKey
	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}
}
