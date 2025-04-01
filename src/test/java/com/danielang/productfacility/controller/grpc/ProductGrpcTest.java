package com.danielang.productfacility.controller.grpc;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-29 14:23
 **/
@QuarkusTest
class ProductGrpcTest {
	@GrpcClient
	ProductService client;

	@Test
	void testCreateProduct() {
		ProductDTO productDTO = ProductDTO.newBuilder().setTenant("HSBC" + System.currentTimeMillis()).setCode("test").setType("test")
				.setName("test").setAbbrevName("test").setCategory("test").setCurrency("CNY").setDescription("test")
				.setStartDate(System.currentTimeMillis()).setEndDate(System.currentTimeMillis())
				.addIndicators(IndicatorDTO.newBuilder().setKey("key").setValue("value").build()).build();

		CommonResponse indefinitely = client.createProduct(productDTO).await().indefinitely();
		assertThat(indefinitely.getCode(), equalTo("ok"));
	}
}
