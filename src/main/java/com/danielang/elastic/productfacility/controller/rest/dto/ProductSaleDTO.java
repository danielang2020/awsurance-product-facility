package com.danielang.elastic.productfacility.controller.rest.dto;

import com.danielang.elastic.productfacility.domain.ProductSale;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-11 22:32
 **/
@Schema(name = "productSale", description = "product sale information")
public record ProductSaleDTO(
		@Schema(name = "productCurrency", description = "product currency", required = true, enumeration = {"USD",
				"SGD"}) String productCurrency,
		@Schema(name = "productStartDate", description = "product start date, timestamp", required = true, examples = "1746973733000", maxLength = 13) long productStartDate,
		@Schema(name = "productEndDate", description = "product end date, timestamp", required = true, examples = "1746973733000", maxLength = 13) long productEndDate,
		@Schema(name = "productSaleIndicators", description = "product sale information indicators", comment = "keys in PRODUCT_SALE_INDICATOR allowed", examples = "{\"DPI_INDICATOR\":\"Non-DPI\", \"PRODUCT_TYPE_FOR_CHANGE_BENEFITS\":\"Traditional\", \"MAS_CLASSIFICATION\":\"Term\"}") Map<String, String> productSaleIndicators)
		implements DTO, DTOConverter<ProductSale> {

	@Override
	public ProductSale convert() {
		return new ProductSale(productCurrency, productStartDate, productEndDate, productSaleIndicators);
	}
}
