package com.danielang.productfacility.controller.grpc;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

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
		ProductDTO productDTO = ProductDTO.newBuilder().setInsuranceTenant("HSBC" + System.currentTimeMillis())
				.setProductCode("test").setProductType("test").setProductName("test").setProductAbbrevName("test")
				.setProductCategory("test").setProductCurrency("CNY").setProductDescription("test")
				.setProductStartDate(System.currentTimeMillis()).setProductEndDate(System.currentTimeMillis())
				.addIndicators(IndicatorDTO.newBuilder().setIndicatorKey("key").setIndicatorValue("value").build())
				.build();

		CommonResponse indefinitely = client.createProduct(productDTO).await().indefinitely();
		assertThat(indefinitely.getResponseCode(), equalTo("ok"));
	}

	@Test
	void testCreateRateTable() {
		RateTableDTO rateTableDTO = RateTableDTO.newBuilder().setInsuranceTenant("HSBC" + System.currentTimeMillis())
				.setRateTableCode("test").setRateTableFactors("a,b,c")
				.addRates(RateDTO.newBuilder().setRateFormat("1,2,3").setRateValue(1.0).build()).build();


		CommonResponse indefinitely = client.createRateTable(rateTableDTO).await().indefinitely();
		assertThat(indefinitely.getResponseCode(), equalTo("ok"));
	}

	@Test
	void testCreateFormula() {
		FormulaDTO build = FormulaDTO.newBuilder().setInsuranceTenant("HSBC" + System.currentTimeMillis())
				.setFormulaCode("test").setFormulaDescription("test").setFormulaExpression("a+b+c")
				.addAllFormulaParameters(List.of("a", "b", "c")).build();

		CommonResponse indefinitely = client.createFormula(build).await().indefinitely();
		assertThat(indefinitely.getResponseCode(), equalTo("ok"));
	}
}
