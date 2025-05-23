package com.danielang.elastic.productfacility.controller.rest.dto;

import com.danielang.elastic.productfacility.domain.Domain;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 22:45
 **/
public sealed interface DTOConverter<T extends Domain>
		permits ProductDTO, ProductInformationDTO, ProductPremiumSARateDTO, ProductSaleDTO {
	T convert();
}
