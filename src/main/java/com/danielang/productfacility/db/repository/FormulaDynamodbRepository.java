package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.FormulaEntity;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-11 10:05
 **/
@ApplicationScoped
public final class FormulaDynamodbRepository implements FormulaRepository{
	public static final String FORMULA_TABLE_NAME = "InsuranceFormula";
	public static final String FORMULA_TABLE_HASH_KEY = "insuranceTenant";
	public static final String FORMULA_TABLE_RANGE_KEY = "formulaCode";

	DynamoDbClient dynamoDB;

	public FormulaDynamodbRepository(DynamoDbClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	@Override
	public boolean save(FormulaEntity formulaEntity) {
		var item = new HashMap<String, AttributeValue>();
		item.put(FORMULA_TABLE_HASH_KEY, AttributeValue.builder().s(formulaEntity.insuranceTenant()).build());
		item.put(FORMULA_TABLE_RANGE_KEY, AttributeValue.builder().s(formulaEntity.formulaCode()).build());
		item.put("formulaDescription", AttributeValue.builder().s(formulaEntity.formulaDescription()).build());
		item.put("formulaExpression", AttributeValue.builder().s(formulaEntity.formulaExpression()).build());
		List<AttributeValue> list = formulaEntity.formulaParameters().stream()
				.map(e -> AttributeValue.builder().s(e).build()).toList();
		item.put("formulaParameters", AttributeValue.builder().l(list).build());

		PutItemRequest putItemRequest = PutItemRequest.builder().tableName(FORMULA_TABLE_NAME).item(item)
				.conditionExpression("attribute_not_exists(" + FORMULA_TABLE_HASH_KEY + ") and attribute_not_exists("
									 + FORMULA_TABLE_RANGE_KEY + ")").build();

		PutItemResponse putItemResponse = dynamoDB.putItem(putItemRequest);

		//todo log all updated field values

		return putItemResponse.sdkHttpResponse().isSuccessful();
	}
}
