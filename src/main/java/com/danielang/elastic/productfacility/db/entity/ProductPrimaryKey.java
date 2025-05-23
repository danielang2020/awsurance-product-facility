package com.danielang.elastic.productfacility.db.entity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-22 13:59
 **/
public record ProductPrimaryKey(String insuranceTenant,
								String productCategory, String productCode, String productType) {
}
