package com.danielang.elastic.productfacility.db.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 22:51
 **/
@RegisterForReflection
@DynamoDbBean
public final class ProductEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT";
	private List<String> specialCollectionFields;
	private List<String> productSections;
	private String pk;
	private String sk;

	public ProductEntity() {
	}

	public ProductEntity(String insuranceTenant, String productCategory, String productCode, String productType,
			List<String> specialCollectionFields, List<String> productSections) {
		this.pk = buildPartitionKey(insuranceTenant, productCategory, productCode, productType);
		this.sk = buildSortKey(SK_SUFFIX);
		this.specialCollectionFields = List.copyOf(specialCollectionFields);
		this.productSections = List.copyOf(productSections);
	}

	@DynamoDbPartitionKey
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	@DynamoDbSortKey
	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	public List<String> getSpecialCollectionFields() {
		return specialCollectionFields;
	}

	public void setSpecialCollectionFields(List<String> specialCollectionFields) {
		this.specialCollectionFields = specialCollectionFields;
	}

	public List<String> getProductSections() {
		return productSections;
	}

	public void setProductSections(List<String> productSections) {
		this.productSections = productSections;
	}
}
