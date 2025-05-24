package com.danielang.elastic.productfacility.controller.rest.dto;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-20 23:28
 **/
public sealed interface DTO
		permits CommonResponseDetailDTO, ProductDTO, ProductInformationDTO, ProductPremiumSARateDTO, ProductSaleDTO,
		ResponseDTO, VoidDTO {
}
