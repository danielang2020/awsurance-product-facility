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
	private static final String PRODUCT_TABLE_NAME = "InsuranceProduct";
	DynamoDbClient dynamoDB;

	public ProductDynamodbRepository(DynamoDbClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	@Override
	public boolean save(ProductEntity productEntity) {
		var item = new HashMap<String, AttributeValue>();
		item.put("tenant", AttributeValue.builder().s(productEntity.tenant()).build());
		item.put("code", AttributeValue.builder().s(productEntity.code()).build());
		item.put("type", AttributeValue.builder().s(productEntity.type()).build());
		item.put("name", AttributeValue.builder().s(productEntity.name()).build());
		item.put("abbrevName", AttributeValue.builder().s(productEntity.abbrevName()).build());
		item.put("category", AttributeValue.builder().s(productEntity.category()).build());
		item.put("currency", AttributeValue.builder().s(productEntity.currency()).build());
		item.put("description", AttributeValue.builder().s(productEntity.description()).build());
		item.put("insertUser", AttributeValue.builder().s(productEntity.insertUser()).build());
		item.put("updateUser", AttributeValue.builder().s(productEntity.updateUser()).build());
		item.put("insertTime", AttributeValue.builder().n(String.valueOf(productEntity.insertTime())).build());
		item.put("updateTime", AttributeValue.builder().n(String.valueOf(productEntity.updateTime())).build());
		productEntity.indicators().forEach(indicator -> item.put("INDICATOR_" + indicator.key(), AttributeValue.builder().s(indicator.value()).build()));

		PutItemRequest putItemRequest = PutItemRequest.builder()
				.tableName(PRODUCT_TABLE_NAME)
				.item(item)
				.conditionExpression("attribute_not_exists(tenant) and attribute_not_exists(code)")
				.build();

		PutItemResponse putItemResponse = dynamoDB.putItem(putItemRequest);

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
	public ProductEntity findByCode(String code) {
		return null;
	}

	@Override
	public ProductEntity findByTenantAndCode(String tenant, String code) {
		return null;
	}
}