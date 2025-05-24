package com.danielang.elastic.productfacility.controller.rest.dto;

import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-24 10:37
 **/
public record ResponseDTO<T extends DTO>(String responseCode, List<CommonResponseDetailDTO> responseDetails, T info)
		implements DTO {

	public static final String OK = "OK";
}
