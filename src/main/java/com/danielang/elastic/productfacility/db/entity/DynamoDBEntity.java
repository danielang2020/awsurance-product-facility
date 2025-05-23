package com.danielang.elastic.productfacility.db.entity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 23:36
 **/
public abstract sealed class DynamoDBEntity
		permits ProductEntity, ProductInformationEntity, ProductPremiumSARateEntity, ProductSaleEntity {
	public static final String SK_PREFIX = "LATEST#";

	protected String buildPartitionKey(String insuranceTenant, String productCategory, String productCode,
			String productType) {
		return String.format("%s#%s#%s#%s", insuranceTenant, productCategory, productCode, productType);
	}

	protected String buildSortKey(String skSuffix) {
		return SK_PREFIX + skSuffix;
	}
}
