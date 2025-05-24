package com.danielang.elastic.productfacility.service;

import com.danielang.elastic.productfacility.convert.ProductInformationMapper;
import com.danielang.elastic.productfacility.convert.ProductMapper;
import com.danielang.elastic.productfacility.convert.ProductPremiumSARateMapper;
import com.danielang.elastic.productfacility.convert.ProductSaleMapper;
import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.db.entity.ProductInformationEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPremiumSARateEntity;
import com.danielang.elastic.productfacility.db.entity.ProductSaleEntity;
import com.danielang.elastic.productfacility.db.repository.ProductRepository;
import com.danielang.elastic.productfacility.domain.Product;
import com.danielang.elastic.productfacility.domain.ProductPKAndSK;
import com.danielang.elastic.productfacility.domain.enums.ProductSection;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 21:47
 **/
@ApplicationScoped
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductInformationMapper productInformationMapper;
	private final ProductPremiumSARateMapper productPremiumSARateMapper;
	private final ProductSaleMapper productSaleMapper;

	public ProductService(ProductRepository productRepository, ProductMapper productMapper,
			ProductInformationMapper productInformationMapper, ProductPremiumSARateMapper productPremiumSARateMapper,
			ProductSaleMapper productSaleMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productInformationMapper = productInformationMapper;
		this.productPremiumSARateMapper = productPremiumSARateMapper;
		this.productSaleMapper = productSaleMapper;
	}

	public void createProductEntity(Product product) {
		product.validate();

		ProductEntity productEntity = productMapper.toEntity(product);
		ProductPKAndSK productPKAndSK = new ProductPKAndSK(productEntity.getPk(), productEntity.getSk());
		ProductInformationEntity productInformationEntity = productInformationMapper.toEntity(
				product.getProductInformation(), productPKAndSK);
		productPKAndSK = new ProductPKAndSK(productEntity.getPk(), productEntity.getSk());
		ProductPremiumSARateEntity productPremiumSARateEntity = productPremiumSARateMapper.toEntity(
				product.getProductPremiumSARate(), productPKAndSK);
		ProductSaleEntity productSaleEntity = productSaleMapper.toEntity(product.getProductSale(), productPKAndSK);

		productRepository.save(productEntity, productInformationEntity, productPremiumSARateEntity, productSaleEntity);
	}

	public void getProductEntity(String insuranceTenant, String productCode, String productSections) {
		if (productSections == null) {
			ProductEntity productEntity = productRepository.findByInsuranceTenantAndProductCode(insuranceTenant,
					productCode);
			ProductInformationEntity productInformationEntity = productRepository.findInformationByInsuranceTenantAndProductCode(
					insuranceTenant, productCode);
			ProductPremiumSARateEntity productPremiumSARateEntity = productRepository.findPremiumSARateByInsuranceTenantAndProductCode(
					insuranceTenant, productCode);
			ProductSaleEntity productSaleEntity = productRepository.findSaleByInsuranceTenantAndProductCode(
					insuranceTenant, productCode);

		} else {
			switch (ProductSection.valueOf(productSections.toUpperCase())) {
				case PRODUCT_INFORMATION ->
						productRepository.findInformationByInsuranceTenantAndProductCode(insuranceTenant, productCode);
				case PRODUCT_PREMIUM_SA_RATE ->
						productRepository.findPremiumSARateByInsuranceTenantAndProductCode(insuranceTenant,
								productCode);
				case PRODUCT_SALE ->
						productRepository.findSaleByInsuranceTenantAndProductCode(insuranceTenant, productCode);
			}
		}
	}
}
