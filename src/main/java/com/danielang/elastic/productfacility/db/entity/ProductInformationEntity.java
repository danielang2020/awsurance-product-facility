package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
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
public final class ProductInformationEntity implements DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_INFORMATION";
	private String pk;
	private String sk;
	private String productName;
	private String productAbbrevName;
	private String productDescription;

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductInformationEntity() {
	}

	public ProductInformationEntity(String insuranceTenant, String productCode, String productName,
			String productAbbrevName, String productDescription) {
		this.pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		this.sk = EntityUtil.buildProductSortKey(SK_SUFFIX);
		this.productName = productName;
		this.productAbbrevName = productAbbrevName;
		this.productDescription = productDescription;
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
