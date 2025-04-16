package com.danielang.productfacility.db.repository;

import com.danielang.productfacility.db.entity.FormulaEntity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-04-11 10:05
 **/
public sealed interface FormulaRepository permits FormulaDynamodbRepository {
	public boolean save(FormulaEntity formulaEntity);
}
