package com.danielang.productfacility.domain.entity;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-15 17:33
 **/
public class RateTable {
	public static final String FACTORS_DELIMITER = ",";
	private String tenant;
	private String code;
	private String factors;
	private List<Rate> rates;

	public RateTable(String tenant, String code, String factors) {
		this.tenant = tenant;
		this.code = code;
		this.factors = factors;
	}

	public RateTable(RateTable rateTable) {
		this.tenant = rateTable.getTenant();
		this.code = rateTable.getCode();
		this.factors = rateTable.getFactors();
		this.rates = (rateTable.rates != null && !rateTable.rates.isEmpty()) ?
				rateTable.rates.stream().map(Rate::new).toList() :
				List.of();
	}

	public String getFactors() {
		return factors;
	}

	public void setFactors(String factors) {
		this.factors = factors;
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

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = (rates != null && !rates.isEmpty()) ? rates.stream().map(Rate::new).toList() : List.of();
	}

	public void validate() {
		if (tenant == null || tenant.isBlank()) {
			throw new IllegalArgumentException("Rate Table Tenant is required");
		}

		if (code == null || code.isBlank()) {
			throw new IllegalArgumentException("Rate Table Code is required");
		}

		if (factors == null || factors.isBlank()) {
			throw new IllegalArgumentException("Rate Table Factors is required");
		}

		if (rates == null || rates.isEmpty()) {
			throw new IllegalArgumentException("Rate Table Rates is required");
		} else {
			rates.forEach(Rate::validate);
		}

		int length = factors.split(FACTORS_DELIMITER).length;
		boolean match = rates.stream().anyMatch(e -> e.getFormat().split(FACTORS_DELIMITER).length != length);
		if (match) {
			throw new IllegalArgumentException("Rate Table Rate Format is not match with Factors");
		}
	}
}
