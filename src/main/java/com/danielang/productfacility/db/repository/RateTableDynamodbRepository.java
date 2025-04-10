package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.RateEntity;
import com.danielang.productfacility.db.entity.RateTableEntity;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:53
 **/
@ApplicationScoped
public final class RateTableDynamodbRepository implements RateTableRepository {
	public static final String RATETABLE_TABLE_NAME = "InsuranceRateTable";
	public static final String RATETABLE_TABLE_HASH_KEY = "insuranceTenant";
	public static final String RATETABLE_TABLE_RANGE_KEY = "rateTableCode";
	public static final String RATETABLE_TABLE_FACTOR_VALUE = "factorValue";
	public static final String RATETABLE_TABLE_RATE_VALUE = "rateValue";
	public static final String RATETABLE_RANGE_KEY_SUFFIX = "_FACTORS";
	public static final String RATETABLE_STATUS = "rateTableStatus";
	public static final String RATETABLE_STATUS_INIT = "init";
	public static final String RATETABLE_STATUS_DONE = "done";
	DynamoDbClient dynamoDB;

	public RateTableDynamodbRepository(DynamoDbClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	@Override
	public boolean save(RateTableEntity rateTableEntity) {
		var item = new HashMap<String, AttributeValue>();
		item.put(RATETABLE_TABLE_HASH_KEY, AttributeValue.builder().s(rateTableEntity.tenant()).build());
		item.put(RATETABLE_TABLE_RANGE_KEY,
				AttributeValue.builder().s(rateTableEntity.code() + RATETABLE_RANGE_KEY_SUFFIX).build());
		item.put(RATETABLE_TABLE_FACTOR_VALUE, AttributeValue.builder().s(rateTableEntity.factors()).build());
		item.put(RATETABLE_STATUS, AttributeValue.builder().s(RATETABLE_STATUS_INIT).build());
		PutItemRequest putItemRequest = PutItemRequest.builder().tableName(RATETABLE_TABLE_NAME).item(item)
				.conditionExpression("attribute_not_exists(" + RATETABLE_TABLE_HASH_KEY + ") and attribute_not_exists("
									 + RATETABLE_TABLE_RANGE_KEY + ")").build();
		PutItemResponse putItemResponse = dynamoDB.putItem(putItemRequest);

		if (putItemResponse.sdkHttpResponse().isSuccessful()) {
			List<RateEntity> rateEntities = rateTableEntity.rates();
			for (RateEntity rateEntity : rateEntities) {
				var rateItem = new HashMap<String, AttributeValue>();
				rateItem.put(RATETABLE_TABLE_HASH_KEY, AttributeValue.builder().s(rateTableEntity.tenant()).build());
				rateItem.put(RATETABLE_TABLE_RANGE_KEY,
						AttributeValue.builder().s(rateTableEntity.code() + "_" + rateEntity.format()).build());
				rateItem.put(RATETABLE_TABLE_RATE_VALUE,
						AttributeValue.builder().s(rateEntity.value().toPlainString()).build());
				PutItemRequest ratePutItemRequest = PutItemRequest.builder().tableName(RATETABLE_TABLE_NAME)
						.item(rateItem).conditionExpression(
								"attribute_not_exists(" + RATETABLE_TABLE_HASH_KEY + ") and attribute_not_exists("
								+ RATETABLE_TABLE_RANGE_KEY + ")").build();
				PutItemResponse putItemResponse1 = dynamoDB.putItem(ratePutItemRequest);
				if (!putItemResponse1.sdkHttpResponse().isSuccessful()) {
					return false;
				}
			}

			if (!rateEntities.isEmpty()) {
				var keyItem = new HashMap<String, AttributeValue>();
				keyItem.put(RATETABLE_TABLE_HASH_KEY, AttributeValue.builder().s(rateTableEntity.tenant()).build());
				keyItem.put(RATETABLE_TABLE_RANGE_KEY,
						AttributeValue.builder().s(rateTableEntity.code() + RATETABLE_RANGE_KEY_SUFFIX).build());

				var updateItem = new HashMap<String, AttributeValueUpdate>();
				updateItem.put(RATETABLE_STATUS, AttributeValueUpdate.builder().value(d -> d.s(RATETABLE_STATUS_DONE))
						.action(AttributeAction.PUT).build());
				UpdateItemRequest updateItemRequest = UpdateItemRequest.builder().tableName(RATETABLE_TABLE_NAME)
						.key(keyItem).attributeUpdates(updateItem).build();
				UpdateItemResponse updateItemResponse = dynamoDB.updateItem(updateItemRequest);

				return updateItemResponse.sdkHttpResponse().isSuccessful();
			}
		}

		return false;
	}
}