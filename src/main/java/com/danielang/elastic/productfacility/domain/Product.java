package com.danielang.elastic.productfacility.domain;


import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPrimaryKey;
import com.danielang.elastic.productfacility.domain.enums.ProductSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Product Aggregate
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:05
 **/
public final class Product implements Domain, DomainConverter<ProductEntity> {
	private List<String> specialCollectionFields;
	private List<String> productSections;
	private String insuranceTenant;
	private String productCategory;
	private String productCode;
	private String productType;
	private ProductInformation productInformation;
	private ProductSale productSale;
	private ProductPremiumSARate productPremiumSARate;

	public Product(List<String> specialCollectionFields, List<String> productSections, String insuranceTenant,
			String productCategory, String productCode, String productType, ProductInformation productInformation,
			ProductSale productSale, ProductPremiumSARate productPremiumSARate) {
		this.specialCollectionFields = new ArrayList<>(specialCollectionFields);
		this.productSections = new ArrayList<>(productSections);
		this.insuranceTenant = insuranceTenant;
		this.productCategory = productCategory;
		this.productCode = productCode;
		this.productType = productType;
		this.productInformation = productInformation;
		this.productSale = productSale;
		this.productPremiumSARate = productPremiumSARate;
	}

	public List<String> getSpecialCollectionFields() {
		return new ArrayList<>(specialCollectionFields);
	}

	public List<String> getProductSections() {
		return new ArrayList<>(productSections);
	}

	public String getInsuranceTenant() {
		return insuranceTenant;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductType() {
		return productType;
	}

	public ProductInformation getProductInformation() {
		return productInformation;
	}

	public ProductSale getProductSale() {
		return productSale;
	}

	public ProductPremiumSARate getProductPremiumSARate() {
		return productPremiumSARate;
	}

	@Override
	public void validate() {
		productSections.forEach(section -> {
			ProductSection productSection = ProductSection.valueOf(section);
			if (ProductSection.PRODUCT_INFORMATION == productSection) {
				productInformation.validate();
			}

			if (ProductSection.PRODUCT_SALE == productSection) {
				productSale.validate();
			}

			if (ProductSection.PRODUCT_PREMIUM_SA_RATE == productSection) {
				productPremiumSARate.validate();
			}
		});
	}

	@Override
	public ProductEntity convert(Object... args) {
		return new ProductEntity(getInsuranceTenant(), getProductCategory(), getProductCode(), getProductType(),
				getSpecialCollectionFields(), getProductSections());
	}

	public ProductPrimaryKey getProductPrimaryKey() {
		return new ProductPrimaryKey(getInsuranceTenant(), getProductCategory(), getProductCode(), getProductType());
	}
}
