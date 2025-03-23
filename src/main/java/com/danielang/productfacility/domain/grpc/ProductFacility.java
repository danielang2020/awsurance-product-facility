package com.danielang.productfacility.domain.grpc;

import com.danielang.productfacility.grpc.CommonResponse;
import com.danielang.productfacility.grpc.Product;
import com.danielang.productfacility.grpc.ProductService;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * product facility
 *
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-14 22:44
 **/
@GrpcService
public class ProductFacility implements ProductService {

	@Override
	public Uni<CommonResponse> createProduct(Product request) {
		Expression e = new ExpressionBuilder("x * y")
				.variables("x", "y")
				.build()
				.setVariable("x", Double.parseDouble(request.getTenant()))
				.setVariable("y", Double.parseDouble(request.getCode()));
		double result = e.evaluate();

		return Uni.createFrom().item(() -> CommonResponse.newBuilder().setCode(result+"").build());
	}
}
