package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-21 00:03
 **/
@RegisterForReflection
@DynamoDbBean
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
		this.productSaleIndicators = Map.copyOf(productSaleIndicators);
	}


	public String getProductCurrency() {
		return productCurrency;
	}

	public void setProductCurrency(String productCurrency) {
		this.productCurrency = productCurrency;
	}

	public long getProductStartDate() {
		return productStartDate;
	}

	public void setProductStartDate(long productStartDate) {
		this.productStartDate = productStartDate;
	}

	public long getProductEndDate() {
		return productEndDate;
	}

	public void setProductEndDate(long productEndDate) {
		this.productEndDate = productEndDate;
	}

	public Map<String, String> getProductSaleIndicators() {
		return productSaleIndicators;
	}

	public void setProductSaleIndicators(Map<String, String> productSaleIndicators) {
		this.productSaleIndicators = productSaleIndicators;
	}
}
