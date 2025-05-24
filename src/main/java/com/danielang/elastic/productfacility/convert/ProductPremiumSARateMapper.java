package com.danielang.elastic.productfacility.convert;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductPremiumSARateDTO;
import com.danielang.elastic.productfacility.db.entity.DynamoDBEntity;
import com.danielang.elastic.productfacility.db.entity.ProductPremiumSARateEntity;
import com.danielang.elastic.productfacility.domain.ProductPKAndSK;
import com.danielang.elastic.productfacility.domain.ProductPremiumSARate;
import org.mapstruct.*;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-23 18:19
 **/
@Mapper(componentModel = "cdi")
public interface ProductPremiumSARateMapper {
	ProductPremiumSARateDTO toDTO(ProductPremiumSARate productPremiumSARate);

	ProductPremiumSARate toDomain(ProductPremiumSARateDTO productPremiumSARateDTO);

	@Mapping(target = "pk", expression = "java(context.getPk())")
	ProductPremiumSARateEntity toEntity(ProductPremiumSARate productPremiumSARate, @Context ProductPKAndSK context);

	@AfterMapping
	default void setPKAndSK(@MappingTarget ProductPremiumSARateEntity target) {
		target.setSk(DynamoDBEntity.SK_PREFIX + ProductPremiumSARateEntity.SK_SUFFIX);
	}
}
