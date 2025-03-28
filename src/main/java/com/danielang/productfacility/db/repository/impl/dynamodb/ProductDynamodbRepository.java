package com.danielang.productfacility.db.repository.impl.dynamodb;

import com.danielang.productfacility.db.entity.FormulaEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.db.entity.RateTableEntity;
import com.danielang.productfacility.db.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:53
 **/
@ApplicationScoped
public class ProductDynamodbRepository implements ProductRepository {
	DynamoDbClient dynamoDB;

	public ProductDynamodbRepository(DynamoDbClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}


	@Override
	public void save(ProductEntity productEntity) {
		System.out.println(productEntity);
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
