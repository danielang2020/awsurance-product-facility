package com.danielang.productfacility.service;

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

	public ProductUseCaseService(ProductRepository productRepository, RateTableRepository rateTableRepository,FormulaRepository formulaRepository) {
		this.productRepository = productRepository;
		this.rateTableRepository = rateTableRepository;
		this.formulaRepository = formulaRepository;
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
		String factors = rateTableDTO.getRateTableFactors();
		List<RateDTO> rateDTOs = rateTableDTO.getRatesList();
		List<Rate> list = rateDTOs.stream().map(e -> new Rate(e.getRateFormat(), BigDecimal.valueOf(e.getRateValue())))
				.toList();
		var rateTable = new RateTable(tenant, code, factors,list);

		rateTable.validate();

		List<RateEntity> rateEntities = rateTable.getRates().stream()
				.map(e -> new RateEntity(e.getFormat(), e.getValue())).toList();
		long now = System.currentTimeMillis();
		var rateTableEntity = new RateTableEntity(rateTable.getTenant(), rateTable.getCode(), rateTable.getFactors(),
				rateEntities, "admin", "admin", now, now);
		return rateTableRepository.save(rateTableEntity);
	}

	public boolean createFormula(final FormulaDTO formulaDTO) {
		String insuranceTenant = formulaDTO.getInsuranceTenant();
		String formulaCode = formulaDTO.getFormulaCode();
		String formulaDescription = formulaDTO.getFormulaDescription();
		String formulaExpression = formulaDTO.getFormulaExpression();
		List<String> formulaParameters = formulaDTO.getFormulaParametersList().stream().toList();

		var formula = new Formula(insuranceTenant, formulaCode, formulaDescription, formulaExpression,formulaParameters);

		formula.validate();

		FormulaEntity formulaEntity = new FormulaEntity(formula.getInsuranceTenant(), formula.getFormulaCode(),
				formula.getFormulaDescription(), formula.getFormulaExpression(),formula.getFormulaParameters());

		return formulaRepository.save(formulaEntity);
	}
}