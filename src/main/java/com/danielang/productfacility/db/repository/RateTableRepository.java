package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.RateTableEntity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-01 22:43
 **/
public interface RateTableRepository {
	boolean save(RateTableEntity rateTableEntity);
}
