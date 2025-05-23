package com.danielang.elastic.productfacility.controller.rest;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductDTO;
import com.danielang.elastic.productfacility.domain.Product;
import com.danielang.elastic.productfacility.service.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-11 20:07
 **/
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRESTful {
	private final ProductService productService;

	public ProductRESTful(ProductService productService) {
		this.productService = productService;
	}

	@POST
	public void createProduct(ProductDTO productDTO) {
		Product product = productDTO.convert();
		productService.createProductEntity(product);
	}

	@PUT
	public void updateProduct(ProductDTO productDTO) {

	}

	@Path("/{productCode}")
	@DELETE
	public void deleteProduct(String productCode) {

	}
}
