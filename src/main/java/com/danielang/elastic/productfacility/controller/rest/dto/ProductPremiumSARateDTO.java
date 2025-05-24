package com.danielang.elastic.productfacility.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Map;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-11 23:23
 **/
@Schema(name = "productPremiumSARate", description = "product premium sum assured rate information")
public record ProductPremiumSARateDTO(
		@Schema(name = "calculationAge", description = "define what kind of age to be used to capture premium rate", required = true, enumeration = {
				"ISSUE_AGE", "ATTAINED_AGE"}) String calculationAge,
		@Schema(name = "productPremiumSARateIndicators", description = "product premium sum assured rate indicators", comment = "keys in PRODUCT_PREMIUM_SA_RATE_INDICATOR allowed", examples = "{\"SPECIAL_DISCOUNT_ON_PREMIUM\":\"YES\"}") Map<String, String> productPremiumSARateIndicators)
		implements DTO {

}
