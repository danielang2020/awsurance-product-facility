package com.danielang.elastic.productfacility.db.utils;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-24 10:29
 **/
public class EntityUtil {
	public static final String SK_PREFIX = "LATEST#";

	private EntityUtil() {
	}

	public static String buildProductPartitionKey(String insuranceTenant, String productCode) {
		return String.format("%s#%s", insuranceTenant, productCode);
	}

	public static String buildProductSortKey(String skSuffix) {
		return SK_PREFIX + skSuffix;
	}
}
