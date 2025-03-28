package com.danielang.productfacility.db.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 10:25
 **/
public record ProductEntity(String tenant, String code, String type, String name, String abbrevName, String category,
							String currency, String description, String insertUser, String updateUser, long startDate,
							long endDate, long insertTime, long updateTime, List<IndicatorEntity> indicators) {
}
