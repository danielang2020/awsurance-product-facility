package com.danielang.productfacility.domain.aggregate;


import com.danielang.productfacility.domain.entity.Formula;
import com.danielang.productfacility.domain.entity.Indicator;
import com.danielang.productfacility.domain.entity.RateTable;
import com.danielang.productfacility.domain.util.DomainUtil;

import java.util.List;

/**
 * Product Aggregate
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:05
 **/
public final class Product {
	private String insuranceTenant;
	private String productCode;
	private String productType;
	private String productName;
	private String productAbbrevName;
	private String productCategory;
	private String productCurrency;
	private String productDescription;
	private long productStartDate;
	private long productEndDate;
	private List<Indicator> indicators;

	private List<Formula> formulas;

	private List<RateTable> rateTables;

	public String getInsuranceTenant() {
		return insuranceTenant;
	}

	public void setInsuranceTenant(String insuranceTenant) {
		this.insuranceTenant = insuranceTenant;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAbbrevName() {
		return productAbbrevName;
	}

	public void setProductAbbrevName(String productAbbrevName) {
		this.productAbbrevName = productAbbrevName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductCurrency() {
		return productCurrency;
	}

	public void setProductCurrency(String productCurrency) {
		this.productCurrency = productCurrency;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public long getProductStartDate() {
		return productStartDate;
	}

	public void setProductStartDate(long productStartDate) {
		this.productStartDate = productStartDate;
	}

	public long getProductEndDate() {
		return productEndDate;
	}

	public void setProductEndDate(long productEndDate) {
		this.productEndDate = productEndDate;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		// immutable deep copy list
		this.indicators = (indicators != null && !indicators.isEmpty()) ?
				indicators.stream().map(Indicator::new).toList() :
				List.of();
	}

	public List<Formula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<Formula> formulas) {
		this.formulas = (formulas != null && !formulas.isEmpty()) ?
				formulas.stream().map(Formula::new).toList() :
				List.of();
	}

	public List<RateTable> getRateTables() {
		return rateTables;
	}

	public void setRateTables(List<RateTable> rateTables) {
		this.rateTables = (rateTables != null && !rateTables.isEmpty()) ?
				rateTables.stream().map(RateTable::new).toList() :
				List.of();
	}

	public void validate() {
		DomainUtil.isNullOrBlank(insuranceTenant, "insuranceTenant");
		DomainUtil.isNullOrBlank(productCode, "productCode");
		DomainUtil.isNullOrBlank(productType, "productType");
		DomainUtil.isNullOrBlank(productName, "productName");
		DomainUtil.isNullOrBlank(productAbbrevName, "productAbbrevName");
		DomainUtil.isNullOrBlank(productCategory, "productCategory");
		DomainUtil.isNullOrBlank(productCurrency, "productCurrency");
		DomainUtil.isNullOrBlank(productDescription, "productDescription");

		if (productStartDate == 0) {
			throw new IllegalArgumentException("productStartDate is required");
		}

		if (productEndDate == 0) {
			throw new IllegalArgumentException("productEndDate is required");
		}

		if (productEndDate < productStartDate) {
			throw new IllegalArgumentException("End Date must be greater than Start Date");
		}

		if (indicators != null && !indicators.isEmpty()) {
			indicators.forEach(Indicator::validate);
		}

		if (formulas != null && !formulas.isEmpty()) {
			formulas.forEach(Formula::validate);
		}

		if (rateTables != null && !rateTables.isEmpty()) {
			rateTables.forEach(RateTable::validate);
		}
	}
}
