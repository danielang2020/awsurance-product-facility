package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
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
public final class ProductEntity implements DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT";
	private List<String> specialCollectionFields;
	private List<String> productSections;
	private String pk;
	private String sk;
	private String productCategory;
	private String productType;

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductEntity() {
	}

	public ProductEntity(String insuranceTenant, String productCode, String productCategory, String productType,
			List<String> specialCollectionFields, List<String> productSections) {
		this.pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		this.sk = EntityUtil.buildProductSortKey(SK_SUFFIX);
		this.specialCollectionFields = List.copyOf(specialCollectionFields);
		this.productSections = List.copyOf(productSections);
		this.productCategory = productCategory;
		this.productType = productType;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
