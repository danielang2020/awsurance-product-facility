package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.FormulaEntity;
import com.danielang.productfacility.db.entity.ProductEntity;
import com.danielang.productfacility.db.entity.RateTableEntity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:28
 **/
public interface ProductRepository {
	  void save(ProductEntity productEntity);
	  void delete();
	  void updateByAddingRateTable(String tenant, String code, RateTableEntity rateTableEntity);
	  void updateByAddingFormula(String tenant, String code, FormulaEntity formulaEntity);
	  ProductEntity findByCode(String code);
	  ProductEntity findByTenantAndCode(String tenant, String code);
}
