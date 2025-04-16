package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.FormulaEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.db.entity.RateTableEntity;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:53
 **/
@ApplicationScoped
public final class ProductDynamodbRepository implements ProductRepository {
	public static final String PRODUCT_TABLE_NAME = "InsuranceProduct";
	public static final String PRODUCT_TABLE_HASH_KEY = "insuranceTenant";
	public static final String PRODUCT_TABLE_RANGE_KEY = "productCode";
	public static final String PRODUCT_INDICATOR_PREFIX = "INDICATOR_";
	DynamoDbClient dynamoDB;

	public ProductDynamodbRepository(DynamoDbClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	@Override
	public boolean save(ProductEntity productEntity) {
		var item = new HashMap<String, AttributeValue>();
		item.put(PRODUCT_TABLE_HASH_KEY, AttributeValue.builder().s(productEntity.insuranceTenant()).build());
		item.put(PRODUCT_TABLE_RANGE_KEY, AttributeValue.builder().s(productEntity.productCode()).build());
		item.put("productType", AttributeValue.builder().s(productEntity.productType()).build());
		item.put("productName", AttributeValue.builder().s(productEntity.productAbbrevName()).build());
		item.put("productAbbrevName", AttributeValue.builder().s(productEntity.productAbbrevName()).build());
		item.put("productCategory", AttributeValue.builder().s(productEntity.productCategory()).build());
		item.put("productCurrency", AttributeValue.builder().s(productEntity.productCurrency()).build());
		item.put("productDescription", AttributeValue.builder().s(productEntity.productDescription()).build());
		item.put("productStartDate",
				AttributeValue.builder().n(String.valueOf(productEntity.productStartDate())).build());
		item.put("productEndDate", AttributeValue.builder().n(String.valueOf(productEntity.productEndDate())).build());
		item.put("insertUser", AttributeValue.builder().s(productEntity.insertUser()).build());
		item.put("updateUser", AttributeValue.builder().s(productEntity.updateUser()).build());
		item.put("insertTime", AttributeValue.builder().n(String.valueOf(productEntity.insertTime())).build());
		item.put("updateTime", AttributeValue.builder().n(String.valueOf(productEntity.updateTime())).build());
		productEntity.indicators().forEach(indicator -> item.put(PRODUCT_INDICATOR_PREFIX + indicator.indicatorKey(),
				AttributeValue.builder().s(indicator.indicatorValue()).build()));

		PutItemRequest putItemRequest = PutItemRequest.builder().tableName(PRODUCT_TABLE_NAME).item(item)
				.conditionExpression("attribute_not_exists(" + PRODUCT_TABLE_HASH_KEY + ") and attribute_not_exists("
									 + PRODUCT_TABLE_RANGE_KEY + ")").build();

		PutItemResponse putItemResponse = dynamoDB.putItem(putItemRequest);

		//todo log all updated field values

		return putItemResponse.sdkHttpResponse().isSuccessful();
	}

	@Override
	public void update(ProductEntity productEntity) {

	}

	@Override
	public void delete() {

	}

	@Override
	public void updateByAddingRateTable(String tenant, String code, RateTableEntity rateTableEntity) {

	}

	@Override
	public void updateByAddingFormula(String tenant, String code, FormulaEntity formulaEntity) {

	}

	@Override
	public ProductEntity findByTenantAndCode(String tenant, String code) {
		return null;
	}
}