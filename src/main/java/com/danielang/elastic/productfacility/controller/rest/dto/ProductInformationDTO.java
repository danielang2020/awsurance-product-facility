package com.danielang.elastic.productfacility.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-12 21:01
 **/
@Schema(name = "productInformation", description = "product information")
public record ProductInformationDTO(
		@Schema(name = "productName", description = "product full name", required = true, examples = "Living Term Assurance", maxLength = 100) String productName,
		@Schema(name = "productAbbrevName", description = "product short name", examples = "Living Term Assurance", maxLength = 50) String productAbbrevName,
		@Schema(name = "productDescription", description = "product description", required = true, examples = "A non-participation plan with a dread disease accelerator on the sum assured.", maxLength = 500) String productDescription)
		implements DTO {

}
