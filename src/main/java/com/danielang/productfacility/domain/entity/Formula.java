package com.danielang.productfacility.domain.entity;

import com.danielang.productfacility.domain.util.DomainUtil;

import java.util.List;

/**
 * Product Formula Entity
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:10
 **/
public class Formula {
	private String insuranceTenant;
	private String formulaCode;
	private String formulaDescription;
	private String formulaExpression;
	private List<String> formulaParameters;

	public Formula(String insuranceTenant, String formulaCode, String formulaDescription, String formulaExpression,
			List<String> formulaParameters) {
		this.insuranceTenant = insuranceTenant;
		this.formulaCode = formulaCode;
		this.formulaDescription = formulaDescription;
		this.formulaExpression = formulaExpression;
		this.formulaParameters = (formulaParameters != null && !formulaParameters.isEmpty()) ?
				formulaParameters.stream().map(String::new).toList() :
				List.of();
	}

	public Formula(Formula formula) {
		this.insuranceTenant = formula.insuranceTenant;
		this.formulaCode = formula.formulaCode;
		this.formulaDescription = formula.formulaDescription;
		this.formulaExpression = formula.formulaExpression;
		this.formulaParameters = formula.formulaParameters;
		this.formulaParameters = (formulaParameters != null && !formulaParameters.isEmpty()) ?
				formulaParameters.stream().map(String::new).toList() :
				List.of();
	}

	public String getInsuranceTenant() {
		return insuranceTenant;
	}

	public void setInsuranceTenant(String insuranceTenant) {
		this.insuranceTenant = insuranceTenant;
	}

	public String getFormulaCode() {
		return formulaCode;
	}

	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}

	public String getFormulaDescription() {
		return formulaDescription;
	}

	public void setFormulaDescription(String formulaDescription) {
		this.formulaDescription = formulaDescription;
	}

	public String getFormulaExpression() {
		return formulaExpression;
	}

	public void setFormulaExpression(String formulaExpression) {
		this.formulaExpression = formulaExpression;
	}

	public List<String> getFormulaParameters() {
		return formulaParameters;
	}

	public void setFormulaParameters(List<String> formulaParameters) {
		this.formulaParameters = (formulaParameters != null && !formulaParameters.isEmpty()) ?
				formulaParameters.stream().map(String::new).toList() :
				List.of();
	}

	public void validate() {
		DomainUtil.isNullOrBlank(insuranceTenant, "insuranceTenant");
		DomainUtil.isNullOrBlank(formulaCode, "formulaCode");
		DomainUtil.isNullOrBlank(formulaDescription, "formulaDescription");
		DomainUtil.isNullOrBlank(formulaExpression, "formulaExpression");

		if (formulaParameters == null || formulaParameters.isEmpty()) {
			throw new IllegalArgumentException("formulaParameters is required");
		}else{
			formulaParameters.forEach(e->{
				boolean contains = formulaExpression.contains(e);
				if (!contains) {
					throw new IllegalArgumentException("formulaExpression must contain formulaParameters");
				}
			});
		}

	}
}
