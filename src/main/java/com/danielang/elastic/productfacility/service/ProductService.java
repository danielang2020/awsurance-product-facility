package com.danielang.elastic.productfacility.service;

import com.danielang.elastic.productfacility.db.entity.*;
import com.danielang.elastic.productfacility.db.repository.ProductRepository;
import com.danielang.elastic.productfacility.domain.Product;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 21:47
 **/
@ApplicationScoped
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void createProductEntity(Product product) {
		product.validate();

		ProductPrimaryKey productPrimaryKey = product.getProductPrimaryKey();

		ProductEntity productEntity = product.convert();
		ProductInformationEntity productInformationEntity = product.getProductInformation().convert(productPrimaryKey);
		ProductPremiumSARateEntity productPremiumSARateEntity = product.getProductPremiumSARate()
				.convert(productPrimaryKey);
		ProductSaleEntity productSaleEntity = product.getProductSale().convert(productPrimaryKey);
		productRepository.save(productEntity, productInformationEntity, productPremiumSARateEntity, productSaleEntity);
	}
}
