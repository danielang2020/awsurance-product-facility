package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-21 00:01
 **/
@RegisterForReflection
@DynamoDbBean
public final class ProductPremiumSARateEntity implements DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_PREMIUM_SA_RATE";
	private String pk;
	private String sk;
	private String calculationAge;
	private Map<String, String> productPremiumSARateIndicators;


	public ProductPremiumSARateEntity(String insuranceTenant, String productCode, String calculationAge,
			Map<String, String> productPremiumSARateIndicators) {
		this.pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		this.sk = EntityUtil.buildProductSortKey(SK_SUFFIX);
		this.calculationAge = calculationAge;
		this.productPremiumSARateIndicators = Map.copyOf(productPremiumSARateIndicators);
	}

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductPremiumSARateEntity() {
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

	public String getCalculationAge() {
		return calculationAge;
	}

	public void setCalculationAge(String calculationAge) {
		this.calculationAge = calculationAge;
	}

	public Map<String, String> getProductPremiumSARateIndicators() {
		return productPremiumSARateIndicators;
	}

	public void setProductPremiumSARateIndicators(Map<String, String> productPremiumSARateIndicators) {
		this.productPremiumSARateIndicators = productPremiumSARateIndicators;
	}
}
