package com.danielang.productfacility.domain.entity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-15 17:59
 **/
public class Indicator{
	private String key;
	private String value;

	public Indicator(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Indicator(Indicator indicator) {
		this.key = indicator.getKey();
		this.value = indicator.getValue();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void validate() {
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException("Indicator key is required");
		}

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Indicator value is required");
		}
	}
}
