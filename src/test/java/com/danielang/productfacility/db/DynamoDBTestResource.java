package com.danielang.productfacility.db;

import com.danielang.productfacility.db.repository.ProductDynamodbRepository;
import com.danielang.productfacility.db.repository.RateTableDynamodbRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-01 21:20
 **/
@QuarkusTestResource(DynamoDBTestResource.class)
public class DynamoDBTestResource implements QuarkusTestResourceLifecycleManager {

	private static final LocalStackContainer LOCALSTACK = new LocalStackContainer(
			DockerImageName.parse("localstack/localstack:latest")).withServices(LocalStackContainer.Service.DYNAMODB);

	@Override
	public Map<String, String> start() {
		LOCALSTACK.start();

		String dynamoDbEndpoint = LOCALSTACK.getEndpointOverride(LocalStackContainer.Service.DYNAMODB).toString();

		DynamoDbClient dynamoDbClient = DynamoDbClient.builder().endpointOverride(URI.create(dynamoDbEndpoint))
				.region(Region.US_EAST_1).credentialsProvider(
						StaticCredentialsProvider.create(AwsBasicCredentials.create("accessKey", "secretKey"))).build();

		dynamoDbClient.createTable(CreateTableRequest.builder().tableName(ProductDynamodbRepository.PRODUCT_TABLE_NAME)
				.keySchema(List.of(KeySchemaElement.builder()
								.attributeName(ProductDynamodbRepository.PRODUCT_TABLE_HASH_KEY).keyType(KeyType.HASH).build(),
						KeySchemaElement.builder().attributeName(ProductDynamodbRepository.PRODUCT_TABLE_RANGE_KEY)
								.keyType(KeyType.RANGE).build())).attributeDefinitions(
						List.of(AttributeDefinition.builder()
								.attributeName(ProductDynamodbRepository.PRODUCT_TABLE_HASH_KEY).attributeType("S")
								.build(), AttributeDefinition.builder()
								.attributeName(ProductDynamodbRepository.PRODUCT_TABLE_RANGE_KEY).attributeType("S")
								.build())).billingMode(BillingMode.PAY_PER_REQUEST).build());


		dynamoDbClient.createTable(CreateTableRequest.builder().tableName(RateTableDynamodbRepository.RATETABLE_TABLE_NAME)
				.keySchema(List.of(KeySchemaElement.builder()
								.attributeName(RateTableDynamodbRepository.RATETABLE_TABLE_HASH_KEY).keyType(KeyType.HASH).build(),
						KeySchemaElement.builder().attributeName(RateTableDynamodbRepository.RATETABLE_TABLE_RANGE_KEY)
								.keyType(KeyType.RANGE).build())).attributeDefinitions(
						List.of(AttributeDefinition.builder()
								.attributeName(RateTableDynamodbRepository.RATETABLE_TABLE_HASH_KEY).attributeType("S")
								.build(), AttributeDefinition.builder()
								.attributeName(RateTableDynamodbRepository.RATETABLE_TABLE_RANGE_KEY).attributeType("S")
								.build())).billingMode(BillingMode.PAY_PER_REQUEST).build());

		return Map.of("quarkus.dynamodb.endpoint-override", dynamoDbEndpoint, "quarkus.dynamodb.aws.region",
				"us-east-1", "quarkus.dynamodb.aws.credentials.type", "static",
				"quarkus.dynamodb.aws.credentials.static-provider.access-key-id", "localAccessKeyId",
				"quarkus.dynamodb.aws.credentials.static-provider.secret-access-key", "localSecretAccessKey");
	}

	@Override
	public void stop() {
		LOCALSTACK.stop();
	}
}
