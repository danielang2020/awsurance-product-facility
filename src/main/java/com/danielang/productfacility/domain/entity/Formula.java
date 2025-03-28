package com.danielang.productfacility.domain.entity;

/**
 * Product Formula Entity
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 23:10
 **/
public class Formula {
	private String name;
	private String expression;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void validate() {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Formula Name is required");
		}

		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException("Formula Expression is required");
		}
	}
}
