package com.danielang.productfacility.domain.util;

import com.danielang.productfacility.controller.grpc.ProductDTO;
import com.danielang.productfacility.db.entity.IndicatorEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.domain.aggregate.Product;
import com.danielang.productfacility.domain.entity.Indicator;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-01 20:58
 **/
public class DomainUtil {
	private DomainUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static void isNullOrBlank(String str, String fieldName) {
		if (str == null || str.isBlank()) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
	}

	public static void isNullOrBlank(BigDecimal bigDecimal, String fieldName) {
		if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
	}

	public static ProductEntity buildProductEntity(Product product) {
		List<IndicatorEntity> indicatorEntities = product.getIndicators().stream()
				.map(e -> new IndicatorEntity(e.getKey(), e.getValue())).toList();
		long now = System.currentTimeMillis();
		//todo oauth2
		return new ProductEntity(product.getInsuranceTenant(), product.getProductCode(), product.getProductType(),
				product.getProductName(), product.getProductAbbrevName(), product.getProductCategory(),
				product.getProductCurrency(), product.getProductDescription(), "admin", "admin",
				product.getProductStartDate(), product.getProductEndDate(), now, now, indicatorEntities);
	}

	public static Product buildProduct(ProductDTO productDTO) {
		var product = new Product();
		product.setInsuranceTenant(productDTO.getInsuranceTenant());
		product.setProductCode(productDTO.getProductCode());
		product.setProductType(productDTO.getProductType());
		product.setProductName(productDTO.getProductName());
		product.setProductAbbrevName(productDTO.getProductAbbrevName());
		product.setProductCategory(productDTO.getProductCategory());
		product.setProductCurrency(productDTO.getProductCurrency());
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductStartDate(productDTO.getProductStartDate());
		product.setProductEndDate(productDTO.getProductEndDate());

		List<Indicator> indicators = productDTO.getIndicatorsList().stream()
				.map(e -> new Indicator(e.getIndicatorKey(), e.getIndicatorValue())).toList();

		product.setIndicators(indicators);

		return product;
	}
}
