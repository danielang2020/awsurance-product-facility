package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 22:39
 **/
@RegisterForReflection
@DynamoDbBean
public final class ProductInformationEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_INFORMATION";
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
		setPk(EntityUtil.buildProductPartitionKey(insuranceTenant, productCode));
		setSk(EntityUtil.buildProductSortKey(SK_SUFFIX));
		this.productName = productName;
		this.productAbbrevName = productAbbrevName;
		this.productDescription = productDescription;
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
