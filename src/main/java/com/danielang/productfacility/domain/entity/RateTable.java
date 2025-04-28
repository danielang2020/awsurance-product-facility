package com.danielang.productfacility.domain.entity;

import com.danielang.productfacility.domain.util.DomainUtil;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-15 17:33
 **/
public class RateTable {
	public static final String FACTORS_DELIMITER = ",";
	private String insuranceTenant;
	private String rateTableCode;
	private String rateTableDescription;
	private String rateTableFactors;
	private List<Rate> rates;

	public RateTable(String insuranceTenant, String rateTableCode,String rateTableDescription, String rateTableFactors, List<Rate> rates) {
		this.insuranceTenant = insuranceTenant;
		this.rateTableCode = rateTableCode;
		this.rateTableDescription = rateTableDescription;
		this.rateTableFactors = rateTableFactors;
		this.rates = (rates != null && !rates.isEmpty()) ? rates.stream().map(Rate::new).toList() : List.of();
	}

	public RateTable(RateTable rateTable) {
		this.insuranceTenant = rateTable.getInsuranceTenant();
		this.rateTableCode = rateTable.getRateTableCode();
		this.rateTableDescription = rateTable.getRateTableDescription();
		this.rateTableFactors = rateTable.getRateTableFactors();
		this.rates = (rateTable.rates != null && !rateTable.rates.isEmpty()) ?
				rateTable.rates.stream().map(Rate::new).toList() :
				List.of();
	}

	public String getInsuranceTenant() {
		return insuranceTenant;
	}

	public void setInsuranceTenant(String insuranceTenant) {
		this.insuranceTenant = insuranceTenant;
	}

	public String getRateTableCode() {
		return rateTableCode;
	}

	public void setRateTableCode(String rateTableCode) {
		this.rateTableCode = rateTableCode;
	}

	public String getRateTableDescription() {
		return rateTableDescription;
	}

	public void setRateTableDescription(String rateTableDescription) {
		this.rateTableDescription = rateTableDescription;
	}

	public String getRateTableFactors() {
		return rateTableFactors;
	}

	public void setRateTableFactors(String rateTableFactors) {
		this.rateTableFactors = rateTableFactors;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = (rates != null && !rates.isEmpty()) ? rates.stream().map(Rate::new).toList() : List.of();
	}

	public void validate() {
		DomainUtil.isNullOrBlank(insuranceTenant, "insuranceTenant");
		DomainUtil.isNullOrBlank(rateTableCode, "rateTableCode");
		DomainUtil.isNullOrBlank(rateTableFactors, "rateTableFactors");

		if (rates == null || rates.isEmpty()) {
			throw new IllegalArgumentException("Rate Table Rates is required");
		} else {
			rates.forEach(Rate::validate);
		}

		int length = rateTableFactors.split(FACTORS_DELIMITER).length;
		boolean match = rates.stream().anyMatch(e -> e.getFormat().split(FACTORS_DELIMITER).length != length);
		if (match) {
			throw new IllegalArgumentException("Rate Table Rate Format is not match with Factors");
		}
	}
}
