package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-21 00:01
 **/
@RegisterForReflection
@DynamoDbBean
@Data
public final class ProductPremiumSARateEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_PREMIUM_SA_RATE";
	private String calculationAge;
	private Map<String, String> productPremiumSARateIndicators;

	public ProductPremiumSARateEntity(String insuranceTenant, String productCode, String calculationAge,
			Map<String, String> productPremiumSARateIndicators) {
		setPk(EntityUtil.buildProductPartitionKey(insuranceTenant, productCode));
		setSk(EntityUtil.buildProductSortKey(SK_SUFFIX));
		this.calculationAge = calculationAge;
		this.productPremiumSARateIndicators = productPremiumSARateIndicators != null ?
				new HashMap<>(productPremiumSARateIndicators) :
				HashMap.newHashMap(0);
	}

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductPremiumSARateEntity() {
	}

	public Map<String, String> getProductPremiumSARateIndicators() {
		return new HashMap<>(productPremiumSARateIndicators);
	}

	public void setProductPremiumSARateIndicators(Map<String, String> productPremiumSARateIndicators) {
		this.productPremiumSARateIndicators = new HashMap<>(productPremiumSARateIndicators);
	}
}
