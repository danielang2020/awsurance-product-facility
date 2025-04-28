package com.danielang.productfacility.db.entity;

import java.math.BigDecimal;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-02 22:09
 **/
public record RateEntity(String rateFormat, BigDecimal rateValue) {
}
