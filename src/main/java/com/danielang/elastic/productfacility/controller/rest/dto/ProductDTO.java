package com.danielang.elastic.productfacility.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-11 21:02
 * limitation: since an item in dynamodb is limited to 400KB, so the product information should be limited to 400KB = 409,600 bytes.
 **/
@Schema(name = "Product", description = "product information")
@Data
public final class ProductDTO implements DTO {
	@Schema(name = "productCategory", description = "product category", required = true, enumeration = {"WHOLE_LIFE",
			"TERM", "DREAD_DISEASE"})
	@NotBlank(message = "productCategory not blank")
	private String productCategory;
	@Schema(name = "productCode", description = "product code, tenant and code = unique key", required = true, examples = "LTAA", maxLength = 20)
	private String productCode;
	@Schema(name = "productType", description = "product type, BASIC or RIDER", required = true, enumeration = {"BASIC",
			"RIDER"})
	private String productType;
	@Schema(name = "specialCollectionFields", description = "which special personal fields need to be collected when sells this product", required = true, examples = "[\"COUNTRY\", \"IDENTIFICATION_TYPE\", \"IDENTIFICATION_NO\"]", enumeration = "[\"COUNTRY\", \"IDENTIFICATION_TYPE\", \"IDENTIFICATION_NO\"]")
	private List<String> specialCollectionFields;
	@Schema(name = "productSections", description = "which sections need to be stored when add/update a product", required = true, examples = "[\"PRODUCT_INFORMATION\", \"PRODUCT_SALE\", \"PRODUCT_PREMIUM_SA_RATE\"]", enumeration = "[\"PRODUCT_INFORMATION\", \"PRODUCT_SALE\", \"PRODUCT_PREMIUM_SA_RATE\"]")
	private List<String> productSections;
	@JsonProperty("productInformation")
	private ProductInformationDTO productInformationDTO;
	@JsonProperty("productSale")
	private ProductSaleDTO productSaleDTO;
	@JsonProperty("productPremiumSARate")
	private ProductPremiumSARateDTO productPremiumSARateDTO;
	@Schema(name = "insuranceTenant", description = "tenant id, for multi-tenant", required = true, examples = "uuubee", maxLength = 20)
	@NotBlank(message = "insuranceTenant not blank")
	@Max(message = "insuranceTenant max 20", value = 20)
	private String insuranceTenant;
}
