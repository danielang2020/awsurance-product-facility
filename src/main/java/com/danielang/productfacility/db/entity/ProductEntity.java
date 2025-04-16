package com.danielang.productfacility.db.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 10:25
 **/
public record ProductEntity(String insuranceTenant, String productCode, String productType, String productName, String productAbbrevName, String productCategory,
							String productCurrency, String productDescription, String insertUser, String updateUser, long productStartDate,
							long productEndDate, long insertTime, long updateTime, List<IndicatorEntity> indicators) {
}
