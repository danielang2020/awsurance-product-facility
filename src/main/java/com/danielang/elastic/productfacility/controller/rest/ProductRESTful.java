package com.danielang.elastic.productfacility.controller.rest;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductDTO;
import com.danielang.elastic.productfacility.convert.ProductInformationMapper;
import com.danielang.elastic.productfacility.convert.ProductMapper;
import com.danielang.elastic.productfacility.convert.ProductPremiumSARateMapper;
import com.danielang.elastic.productfacility.convert.ProductSaleMapper;
import com.danielang.elastic.productfacility.domain.Product;
import com.danielang.elastic.productfacility.domain.ProductInformation;
import com.danielang.elastic.productfacility.domain.ProductPremiumSARate;
import com.danielang.elastic.productfacility.domain.ProductSale;
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

	private final ProductMapper productMapper;
	private final ProductInformationMapper productInformationMapper;
	private final ProductPremiumSARateMapper productPremiumSARateMapper;
	private final ProductSaleMapper productSaleMapper;

	public ProductRESTful(ProductService productService, ProductMapper productMapper,
			ProductInformationMapper productInformationMapper, ProductPremiumSARateMapper productPremiumSARateMapper,
			ProductSaleMapper productSaleMapper) {
		this.productService = productService;
		this.productMapper = productMapper;
		this.productInformationMapper = productInformationMapper;
		this.productPremiumSARateMapper = productPremiumSARateMapper;
		this.productSaleMapper = productSaleMapper;
	}

	@POST
	public void createProduct(ProductDTO productDTO) {
		Product product = productMapper.toDomain(productDTO);
		ProductInformation productInformation = productInformationMapper.toDomain(productDTO.productInformationDTO());
		ProductPremiumSARate productPremiumSARate = productPremiumSARateMapper.toDomain(
				productDTO.productPremiumSARateDTO());
		ProductSale productSale = productSaleMapper.toDomain(productDTO.productSaleDTO());
		product.setProductInformation(productInformation);
		product.setProductPremiumSARate(productPremiumSARate);
		product.setProductSale(productSale);
		productService.createProductEntity(product);
	}

	@Path("/{insuranceTenant}/{productCode}/{productSections}")
	@GET
	public void getProduct(@PathParam("insuranceTenant") String insuranceTenant,
			@PathParam("productCode") String productCode, @PathParam("productSections") String productSections) {

	}

	@Path("/{insuranceTenant}/{productCode}")
	@GET
	public void getProduct(@PathParam("insuranceTenant") String insuranceTenant,
			@PathParam("productCode") String productCode) {

	}
}
