package com.danielang.elastic.productfacility.db.entity;

import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 22:51
 **/
@RegisterForReflection
@DynamoDbBean
@Data
public final class ProductEntity extends DynamoDBEntity {
	public static final String SK_SUFFIX = "PRODUCT";
	private List<String> specialCollectionFields;
	private List<String> productSections;
	private String productCategory;
	private String productType;

	/**
	 * warning: default constructor is required by DynamoDB and mapstruct, don't use it in biz logic.
	 */
	public ProductEntity() {
	}

	public ProductEntity(String insuranceTenant, String productCode, String productCategory, String productType,
			List<String> specialCollectionFields, List<String> productSections) {
		setPk(EntityUtil.buildProductPartitionKey(insuranceTenant, productCode));
		setSk(EntityUtil.buildProductSortKey(SK_SUFFIX));
		this.specialCollectionFields =
				specialCollectionFields != null ? new ArrayList<>(specialCollectionFields) : new ArrayList<>(0);
		this.productSections = productSections != null ? new ArrayList<>(productSections) : new ArrayList<>(0);
		this.productCategory = productCategory;
		this.productType = productType;
	}

	public List<String> getSpecialCollectionFields() {
		return new ArrayList<>(specialCollectionFields);
	}

	public void setSpecialCollectionFields(List<String> specialCollectionFields) {
		this.specialCollectionFields = new ArrayList<>(specialCollectionFields);
	}

	public List<String> getProductSections() {
		return new ArrayList<>(productSections);
	}

	public void setProductSections(List<String> productSections) {
		this.productSections = new ArrayList<>(productSections);
	}
}
