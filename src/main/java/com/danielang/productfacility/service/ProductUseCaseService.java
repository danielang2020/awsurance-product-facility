package com.danielang.productfacility.service;

import com.danielang.calculation.grpc.Calculation;
import com.danielang.calculation.grpc.MutinyCalculatorServiceGrpc;
import com.danielang.productfacility.controller.grpc.FormulaDTO;
import com.danielang.productfacility.controller.grpc.ProductDTO;
import com.danielang.productfacility.controller.grpc.RateDTO;
import com.danielang.productfacility.controller.grpc.RateTableDTO;
import com.danielang.productfacility.db.entity.FormulaEntity;
import com.danielang.productfacility.db.entity.RateEntity;
import com.danielang.productfacility.db.entity.RateTableEntity;
import com.danielang.productfacility.db.repository.FormulaRepository;
import com.danielang.productfacility.db.repository.ProductRepository;
import com.danielang.productfacility.db.repository.RateTableRepository;
import com.danielang.productfacility.domain.entity.Formula;
import com.danielang.productfacility.domain.entity.Rate;
import com.danielang.productfacility.domain.entity.RateTable;
import com.danielang.productfacility.domain.util.DomainUtil;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-26 18:52
 **/
@ApplicationScoped
public class ProductUseCaseService {
	private final ProductRepository productRepository;
	private final RateTableRepository rateTableRepository;
	private final FormulaRepository formulaRepository;
	private final MutinyCalculatorServiceGrpc.MutinyCalculatorServiceStub calculateService;

	public ProductUseCaseService(ProductRepository productRepository, RateTableRepository rateTableRepository,
			FormulaRepository formulaRepository, @GrpcClient MutinyCalculatorServiceGrpc.MutinyCalculatorServiceStub calculateService) {
		this.productRepository = productRepository;
		this.rateTableRepository = rateTableRepository;
		this.formulaRepository = formulaRepository;
		this.calculateService = calculateService;
	}

	public boolean createProduct(final ProductDTO productDTO) {
		var product = DomainUtil.buildProduct(productDTO);

		product.validate();

		var productEntity = DomainUtil.buildProductEntity(product);

		return productRepository.save(productEntity);
	}

	public boolean createRateTable(final RateTableDTO rateTableDTO) {
		String tenant = rateTableDTO.getInsuranceTenant();
		String code = rateTableDTO.getRateTableCode();
		String rateTableDescription = rateTableDTO.getRateTableDescription();
		String factors = rateTableDTO.getRateTableFactors();
		List<RateDTO> rateDTOs = rateTableDTO.getRatesList();
		List<Rate> list = rateDTOs.stream().map(e -> new Rate(e.getRateFormat(), BigDecimal.valueOf(e.getRateValue())))
				.toList();
		var rateTable = new RateTable(tenant, code, rateTableDescription, factors, list);

		rateTable.validate();

		List<RateEntity> rateEntities = rateTable.getRates().stream()
				.map(e -> new RateEntity(e.getFormat(), e.getValue())).toList();
		long now = System.currentTimeMillis();
		var rateTableEntity = new RateTableEntity(rateTable.getInsuranceTenant(), rateTable.getRateTableCode(),
				rateTable.getRateTableDescription(), rateTable.getRateTableFactors(), rateEntities, "admin", "admin",
				now, now);
		return rateTableRepository.save(rateTableEntity);
	}

	public boolean createFormula(final FormulaDTO formulaDTO) {
		String insuranceTenant = formulaDTO.getInsuranceTenant();
		String formulaCode = formulaDTO.getFormulaCode();
		String formulaDescription = formulaDTO.getFormulaDescription();
		String formulaExpression = formulaDTO.getFormulaExpression();
		List<String> formulaParameters = formulaDTO.getFormulaParametersList().stream().toList();
		List<String> rateTableCodes = formulaDTO.getRateTableCodesList().stream().toList();

		var formula = new Formula(insuranceTenant, formulaCode, formulaDescription, formulaExpression,
				formulaParameters, rateTableCodes);

		formula.validate();

		FormulaEntity formulaEntity = new FormulaEntity(formula.getInsuranceTenant(), formula.getFormulaCode(),
				formula.getFormulaDescription(), formula.getFormulaExpression(), formula.getFormulaParameters());

		return formulaRepository.save(formulaEntity);
	}

	public boolean queryRateTableByCode(final String insuranceTenant, final String rateTableCode) {
		return rateTableRepository.queryByTenantAndCode(insuranceTenant, rateTableCode);
	}

	public Uni<Calculation.CalculationResponse> calculateFormula() {
		Calculation.CalculationRequest request = Calculation.CalculationRequest.newBuilder()
				.setCalculationType("LATEX").setExpression("Piecewise((x**2, x < 0), (sin(x)**2 + cos(x)**2, True))")
				.addAllParameters(List.of(Calculation.CalculationParameter.newBuilder().setName("x").setValue(1).build()))
				.build();

		return calculateService.calculate(request);
	}
}