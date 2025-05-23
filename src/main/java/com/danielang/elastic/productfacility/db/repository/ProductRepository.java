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

	boolean update(ProductEntity productEntity);

	boolean update(ProductInformationEntity productInformationEntity);

	boolean update(ProductPremiumSARateEntity productPremiumSARateEntity);
	boolean update(ProductSaleEntity productSaleEntity);

}
