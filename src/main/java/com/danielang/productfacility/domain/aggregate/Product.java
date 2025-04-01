package com.danielang.productfacility.domain.aggregate;


import com.danielang.productfacility.domain.entity.Formula;
import com.danielang.productfacility.domain.entity.Indicator;
import com.danielang.productfacility.domain.entity.RateTable;

import java.util.List;

/**
 * Product Aggregate
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:05
 **/
public final class Product {
	private String tenant;
	private String code;
	private String type;
	private String name;
	private String abbrevName;
	private String category;
	private String currency;
	private String description;
	private long startDate;
	private long endDate;
	private List<Indicator> indicators;

	private List<Formula> formulas;

	private List<RateTable> rateTables;

	private static void isNullOrBlank(String str, String fieldName) {
		if (str == null || str.isBlank()) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevName() {
		return abbrevName;
	}

	public void setAbbrevName(String abbrevName) {
		this.abbrevName = abbrevName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
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
		isNullOrBlank(tenant, "product tenant");

		isNullOrBlank(code, "product code");

		isNullOrBlank(type, "product type");

		isNullOrBlank(name, "product name");

		isNullOrBlank(category, "product category");

		isNullOrBlank(currency, "product currency");

		isNullOrBlank(description, "product description");

		if (startDate == 0) {
			throw new IllegalArgumentException("Start Date is required");
		}

		if (endDate == 0) {
			throw new IllegalArgumentException("End Date is required");
		}

		if (endDate < startDate) {
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
