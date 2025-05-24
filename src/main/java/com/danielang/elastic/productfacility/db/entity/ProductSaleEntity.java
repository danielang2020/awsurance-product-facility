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
 * @create: 2025-05-21 00:03
 **/
@RegisterForReflection
@DynamoDbBean
@Data
public final class ProductSaleEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT_SALE";
	private String productCurrency;
	private long productStartDate;
	private long productEndDate;
	private Map<String, String> productSaleIndicators;

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductSaleEntity() {
	}

	public ProductSaleEntity(String insuranceTenant, String productCode, String productCurrency, long productStartDate,
			long productEndDate, Map<String, String> productSaleIndicators) {
		setPk(EntityUtil.buildProductPartitionKey(insuranceTenant, productCode));
		setSk(EntityUtil.buildProductSortKey(SK_SUFFIX));
		this.productCurrency = productCurrency;
		this.productStartDate = productStartDate;
		this.productEndDate = productEndDate;
		this.productSaleIndicators =
				productSaleIndicators != null ? new HashMap<>(productSaleIndicators) : HashMap.newHashMap(0);
	}


	public Map<String, String> getProductSaleIndicators() {
		return new HashMap<>(productSaleIndicators);
	}

	public void setProductSaleIndicators(Map<String, String> productSaleIndicators) {
		this.productSaleIndicators = new HashMap<>(productSaleIndicators);
	}
}
