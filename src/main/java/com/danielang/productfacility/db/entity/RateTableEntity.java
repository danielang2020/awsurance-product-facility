package com.danielang.productfacility.db.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 10:26
 **/
public record RateTableEntity(String tenant, String code, String factors, List<RateEntity> rates, String insertUser,
							  String updateUser, long insertTime, long updateTime) {

}
