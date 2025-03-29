package com.danielang.productfacility.service;

import com.danielang.productfacility.controller.grpc.ProductDTO;
import com.danielang.productfacility.db.entity.IndicatorEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.db.repository.ProductRepository;
import com.danielang.productfacility.domain.aggregate.Product;
import com.danielang.productfacility.domain.entity.Indicator;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-26 18:52
 **/
@ApplicationScoped
public class ProductUseCaseService {
	private final ProductRepository productRepository;

	public ProductUseCaseService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public boolean createProduct(ProductDTO productDTO) {
		var product = new Product();
		product.setTenant(productDTO.getTenant());
		product.setCode(productDTO.getCode());
		product.setType(productDTO.getType());
		product.setName(productDTO.getName());
		product.setAbbrevName(productDTO.getAbbrevName());
		product.setCategory(productDTO.getCategory());
		product.setCurrency(productDTO.getCurrency());
		product.setDescription(productDTO.getDescription());
		product.setStartDate(productDTO.getStartDate());
		product.setEndDate(productDTO.getEndDate());

		List<Indicator> indicators = productDTO.getIndicatorsList().stream().map(e -> {
			var indicator = new Indicator();
			indicator.setKey(e.getKey());
			indicator.setValue(e.getValue());

			return indicator;
		}).toList();

		product.setIndicators(indicators);

		product.validate();

		List<IndicatorEntity> indicatorEntities = product.getIndicators().stream().map(e -> new IndicatorEntity(e.getKey(), e.getValue())).toList();
		long now = System.currentTimeMillis();
		var productEntity = new ProductEntity(product.getTenant(), product.getCode(), product.getType(), product.getName(), product.getAbbrevName(), product.getCategory(),
				product.getCurrency(), product.getDescription(), "admin", "admin", product.getStartDate(),
				product.getEndDate(), now,now, indicatorEntities);

		return productRepository.save(productEntity);
	}

	public void createRateTable() {

	}

	public void createFormula() {

	}
}
