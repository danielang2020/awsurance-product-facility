package com.danielang.productfacility.service;

import com.danielang.productfacility.controller.grpc.ProductDTO;
import com.danielang.productfacility.db.repository.ProductRepository;
import com.danielang.productfacility.domain.util.DomainUtil;
import jakarta.enterprise.context.ApplicationScoped;

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

	public boolean createProduct(final ProductDTO productDTO) {
		var product = DomainUtil.buildProduct(productDTO);

		product.validate();

		var productEntity = DomainUtil.buildProductEntity(product);

		return productRepository.save(productEntity);
	}

	public void createRateTable() {

	}

	public void createFormula() {

	}
}
