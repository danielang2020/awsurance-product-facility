package com.danielang.productfacility.controller.grpc;

import com.danielang.productfacility.service.ProductUseCaseService;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger logger = LoggerFactory.getLogger(ProductGrpc.class);

	private final ProductUseCaseService productUseCaseService;

	public ProductGrpc(ProductUseCaseService productUseCaseService) {
		this.productUseCaseService = productUseCaseService;
	}


	@Override
	public Uni<CommonResponse> createProduct(final ProductDTO request) {
		try {
			if (productUseCaseService.createProduct(request)) {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("ok").build());
			} else {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("fail").build());
			}
		} catch (Exception e) {
			logger.error("createProductException", e);
			CommonResponseDetail detail = CommonResponseDetail.newBuilder().setResponseDetailKey("msg")
					.setResponseDetailValue(e.getMessage()).build();
			return Uni.createFrom()
					.item(() -> CommonResponse.newBuilder().setResponseCode("error").addAllDetails(List.of(detail))
							.build());
		}
	}

	@Override
	public Uni<CommonResponse> createRateTable(final RateTableDTO request) {
		try {
			if (productUseCaseService.createRateTable(request)) {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("ok").build());
			} else {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("fail").build());
			}
		} catch (Exception e) {
			logger.error("createRateTableException", e);
			CommonResponseDetail detail = CommonResponseDetail.newBuilder().setResponseDetailKey("msg")
					.setResponseDetailValue(e.getMessage()).build();
			return Uni.createFrom()
					.item(() -> CommonResponse.newBuilder().setResponseCode("error").addAllDetails(List.of(detail))
							.build());
		}
	}

	@Override
	public Uni<CommonResponse> createFormula(final FormulaDTO request) {
		try {
			boolean rateTableCheck = request.getRateTableCodesList().stream()
					.anyMatch(e -> !productUseCaseService.queryRateTableByCode(request.getInsuranceTenant(), e));

			if (rateTableCheck) {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("rate table not exist").build());
			}

			if (productUseCaseService.createFormula(request)) {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("ok").build());
			} else {
				return Uni.createFrom().item(() -> CommonResponse.newBuilder().setResponseCode("fail").build());
			}
		} catch (Exception e) {
			logger.error("createFormulaException", e);
			CommonResponseDetail detail = CommonResponseDetail.newBuilder().setResponseDetailKey("msg")
					.setResponseDetailValue(e.getMessage()).build();
			return Uni.createFrom()
					.item(() -> CommonResponse.newBuilder().setResponseCode("error").addAllDetails(List.of(detail))
							.build());
		}
	}

	@Override
	public Uni<CommonResponse> updateProductByAddingFormula(final AddingFormulaDTO request) {
		return null;
	}

	@Override
	public Uni<CommonResponse> updateProductByAddingRateTable(AddingRateTableDTO request) {
		return null;
	}

	@Override
	public Uni<CommonResponse> calculateFormula(CalculateFormulaDTO request) {
	    return productUseCaseService.calculateFormula()
	            .onItem().transform(calculationResponse -> {
	                logger.info("calculateFormulaResponse: {}", calculationResponse.getLatex());
	                return CommonResponse.newBuilder().setResponseCode("ok").build();
	            })
	            .onFailure().recoverWithItem(e -> {
	                logger.error("calculateFormulaException", e);
	                return CommonResponse.newBuilder().setResponseCode("error").build();
	            });
	}
}
