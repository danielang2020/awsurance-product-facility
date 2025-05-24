package com.danielang.elastic.productfacility.domain;


import com.danielang.elastic.productfacility.domain.enums.ProductSection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Product Aggregate
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:05
 **/
@Data
public final class Product implements Domain {
	private List<String> specialCollectionFields;
	private List<String> productSections;
	private String insuranceTenant;
	private String productCategory;
	private String productCode;
	private String productType;
	private ProductInformation productInformation;
	private ProductSale productSale;
	private ProductPremiumSARate productPremiumSARate;

	public Product(String insuranceTenant, String productCategory, String productCode, String productType,
			List<String> specialCollectionFields, List<String> productSections, ProductInformation productInformation,
			ProductSale productSale, ProductPremiumSARate productPremiumSARate) {
		this.specialCollectionFields =
				specialCollectionFields != null ? new ArrayList<>(specialCollectionFields) : new ArrayList<>(0);
		this.productSections = productSections != null ? new ArrayList<>(productSections) : new ArrayList<>(0);
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

	public void setSpecialCollectionFields(List<String> specialCollectionFields) {
		this.specialCollectionFields = new ArrayList<>(specialCollectionFields);
	}

	public List<String> getProductSections() {
		return new ArrayList<>(productSections);
	}

	public void setProductSections(List<String> productSections) {
		this.productSections = new ArrayList<>(productSections);
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
}
