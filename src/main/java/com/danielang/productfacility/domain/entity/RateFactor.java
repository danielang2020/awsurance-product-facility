package com.danielang.productfacility.domain.entity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-15 17:57
 **/
public class RateFactor {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void validate() {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Rate Factor Name is required");
		}

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Rate Factor Value is required");
		}
	}
}
