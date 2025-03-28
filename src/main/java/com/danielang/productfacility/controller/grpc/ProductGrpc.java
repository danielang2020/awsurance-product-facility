package com.danielang.productfacility.controller.grpc;

import com.danielang.productfacility.service.ProductUseCaseService;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

import java.util.List;

/**
 * product facility
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 22:44
 **/
@GrpcService
public class ProductGrpc implements ProductService {
	private final ProductUseCaseService productUseCaseService;

	public ProductGrpc(ProductUseCaseService productUseCaseService) {
		this.productUseCaseService = productUseCaseService;
	}


	@Override
	public Uni<CommonResponse> createProduct(ProductDTO request) {
		try {
			productUseCaseService.createProduct(request);
		} catch (Exception e) {
			CommonResponseDetail detail = CommonResponseDetail.newBuilder().setKey("msg").setValue(e.getMessage())
					.build();
			return Uni.createFrom()
					.item(() -> CommonResponse.newBuilder().setCode("error").addAllDetails(List.of(detail)).build());
		}

		return Uni.createFrom().item(() -> CommonResponse.newBuilder().setCode("ok").build());
	}

	@Override
	public Uni<CommonResponse> createRateTable(RateTableDTO request) {
		return null;
	}

	@Override
	public Uni<CommonResponse> createFormula(FormulaDTO request) {
		return null;
	}

	@Override
	public Uni<CommonResponse> updateProductByAddingFormula(AddingFormulaDTO request) {
		return null;
	}
}
