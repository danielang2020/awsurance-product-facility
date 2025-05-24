package com.danielang.elastic.productfacility.db.repository;

import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.db.entity.ProductInformationEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPremiumSARateEntity;
import com.danielang.elastic.productfacility.db.entity.ProductSaleEntity;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-27 18:28
 **/
public sealed interface ProductRepository permits ProductDynamodbRepository {
	void save(ProductEntity productEntity, ProductInformationEntity productInformationEntity,
			ProductPremiumSARateEntity productPremiumSARateEntity, ProductSaleEntity productSaleEntity);

	ProductEntity findByInsuranceTenantAndProductCode(String insuranceTenant, String productCode);

	ProductInformationEntity findInformationByInsuranceTenantAndProductCode(String insuranceTenant, String productCode);

	ProductPremiumSARateEntity findPremiumSARateByInsuranceTenantAndProductCode(String insuranceTenant,
			String productCode);

	ProductSaleEntity findSaleByInsuranceTenantAndProductCode(String insuranceTenant, String productCode);
}
