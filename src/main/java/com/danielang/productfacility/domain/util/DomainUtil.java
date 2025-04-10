package com.danielang.productfacility.domain.util;

import com.danielang.productfacility.controller.grpc.ProductDTO;
import com.danielang.productfacility.db.entity.IndicatorEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.domain.aggregate.Product;
import com.danielang.productfacility.domain.entity.Indicator;

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

	public static ProductEntity buildProductEntity(Product product) {
		List<IndicatorEntity> indicatorEntities = product.getIndicators().stream()
				.map(e -> new IndicatorEntity(e.getKey(), e.getValue())).toList();
		long now = System.currentTimeMillis();
		//todo oauth2
		return new ProductEntity(product.getTenant(), product.getCode(), product.getType(), product.getName(),
				product.getAbbrevName(), product.getCategory(), product.getCurrency(), product.getDescription(),
				"admin", "admin", product.getStartDate(), product.getEndDate(), now, now, indicatorEntities);
	}

	public static Product buildProduct(ProductDTO productDTO) {
		var product = new Product();
		product.setTenant(productDTO.getInsuranceTenant());
		product.setCode(productDTO.getProductCode());
		product.setType(productDTO.getProductType());
		product.setName(productDTO.getProductName());
		product.setAbbrevName(productDTO.getProductAbbrevName());
		product.setCategory(productDTO.getProductCategory());
		product.setCurrency(productDTO.getProductCurrency());
		product.setDescription(productDTO.getProductDescription());
		product.setStartDate(productDTO.getProductStartDate());
		product.setEndDate(productDTO.getProductEndDate());

		List<Indicator> indicators = productDTO.getIndicatorsList().stream()
				.map(e -> new Indicator(e.getIndicatorKey(), e.getIndicatorValue())).toList();

		product.setIndicators(indicators);

		return product;
	}
}
