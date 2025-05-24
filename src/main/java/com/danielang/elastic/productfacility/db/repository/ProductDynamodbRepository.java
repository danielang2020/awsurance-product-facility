package com.danielang.elastic.productfacility.db.repository;

import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.db.entity.ProductInformationEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPremiumSARateEntity;
import com.danielang.elastic.productfacility.db.entity.ProductSaleEntity;
import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.TransactPutItemEnhancedRequest;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:53
 **/
@ApplicationScoped
public final class ProductDynamodbRepository implements ProductRepository {
	public static final String INSURANCE_PRODUCT = "InsuranceProduct";
	public static final String PRODUCT_INDICATOR_PREFIX = "INDICATOR_";
	private final DynamoDbTable<ProductEntity> productEntityTable;
	private final DynamoDbTable<ProductInformationEntity> productInformationEntityTable;
	private final DynamoDbTable<ProductPremiumSARateEntity> productPremiumSARateEntityTable;
	private final DynamoDbTable<ProductSaleEntity> productSaleEntityTable;
	private final DynamoDbEnhancedClient dynamoEnhancedClient;

	public ProductDynamodbRepository(DynamoDbEnhancedClient dynamoEnhancedClient) {
		this.dynamoEnhancedClient = dynamoEnhancedClient;
		this.productEntityTable = dynamoEnhancedClient.table(INSURANCE_PRODUCT,
				TableSchema.fromClass(ProductEntity.class));
		this.productInformationEntityTable = dynamoEnhancedClient.table(INSURANCE_PRODUCT,
				TableSchema.fromClass(ProductInformationEntity.class));
		this.productPremiumSARateEntityTable = dynamoEnhancedClient.table(INSURANCE_PRODUCT,
				TableSchema.fromClass(ProductPremiumSARateEntity.class));
		this.productSaleEntityTable = dynamoEnhancedClient.table(INSURANCE_PRODUCT,
				TableSchema.fromClass(ProductSaleEntity.class));
	}

	@Override
	public void save(ProductEntity productEntity, ProductInformationEntity productInformationEntity,
			ProductPremiumSARateEntity productPremiumSARateEntity, ProductSaleEntity productSaleEntity) {
		// all product info should be saved in the same transaction
		dynamoEnhancedClient.transactWriteItems(b -> b.addPutItem(productEntityTable,
						TransactPutItemEnhancedRequest.builder(ProductEntity.class).item(productEntity).build())
				.addPutItem(productInformationEntityTable,
						TransactPutItemEnhancedRequest.builder(ProductInformationEntity.class)
								.item(productInformationEntity).build()).addPutItem(productPremiumSARateEntityTable,
						TransactPutItemEnhancedRequest.builder(ProductPremiumSARateEntity.class)
								.item(productPremiumSARateEntity).build()).addPutItem(productSaleEntityTable,
						TransactPutItemEnhancedRequest.builder(ProductSaleEntity.class).item(productSaleEntity)
								.build()));

		//todo log all updated field values
	}

	@Override
	public ProductEntity findByInsuranceTenantAndProductCode(String insuranceTenant, String productCode) {
		String pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		String sk = EntityUtil.buildProductSortKey(ProductEntity.SK_SUFFIX);
		Key key = Key.builder().partitionValue(pk).sortValue(sk).build();

		return productEntityTable.getItem(key);
	}

	@Override
	public ProductInformationEntity findInformationByInsuranceTenantAndProductCode(String insuranceTenant,
			String productCode) {
		String pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		String sk = EntityUtil.buildProductSortKey(ProductInformationEntity.SK_SUFFIX);
		Key key = Key.builder().partitionValue(pk).sortValue(sk).build();
		return productInformationEntityTable.getItem(key);
	}

	@Override
	public ProductPremiumSARateEntity findPremiumSARateByInsuranceTenantAndProductCode(String insuranceTenant,
			String productCode) {
		String pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		String sk = EntityUtil.buildProductSortKey(ProductPremiumSARateEntity.SK_SUFFIX);
		Key key = Key.builder().partitionValue(pk).sortValue(sk).build();
		return productPremiumSARateEntityTable.getItem(key);
	}

	@Override
	public ProductSaleEntity findSaleByInsuranceTenantAndProductCode(String insuranceTenant, String productCode) {
		String pk = EntityUtil.buildProductPartitionKey(insuranceTenant, productCode);
		String sk = EntityUtil.buildProductSortKey(ProductSaleEntity.SK_SUFFIX);
		Key key = Key.builder().partitionValue(pk).sortValue(sk).build();
		return productSaleEntityTable.getItem(key);
	}
}