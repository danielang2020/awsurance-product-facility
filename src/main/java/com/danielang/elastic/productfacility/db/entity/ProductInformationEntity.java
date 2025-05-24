package com.danielang.elastic.productfacility.db.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 22:39
 **/
@RegisterForReflection
@DynamoDbBean
public final class ProductInformationEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_INFORMATION";
	private String pk;
	private String sk;
	private String productName;
	private String productAbbrevName;
	private String productDescription;

	public ProductInformationEntity() {
	}

	public ProductInformationEntity(String insuranceTenant, String productCode, String productName,
			String productAbbrevName, String productDescription) {
		this.pk = buildPartitionKey(insuranceTenant, productCode);
		this.sk = buildSortKey(SK_SUFFIX);
		this.productName = productName;
		this.productAbbrevName = productAbbrevName;
		this.productDescription = productDescription;
	}

	public ProductInformationEntity(String insuranceTenant, String productCode) {
		this.pk = buildPartitionKey(insuranceTenant, productCode);
		this.sk = buildSortKey(SK_SUFFIX);
	}

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAbbrevName() {
		return productAbbrevName;
	}

	public void setProductAbbrevName(String productAbbrevName) {
		this.productAbbrevName = productAbbrevName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}
