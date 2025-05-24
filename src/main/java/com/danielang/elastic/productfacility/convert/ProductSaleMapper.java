package com.danielang.elastic.productfacility.convert;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductSaleDTO;
import com.danielang.elastic.productfacility.db.entity.ProductSaleEntity;
import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import com.danielang.elastic.productfacility.domain.ProductPKAndSK;
import com.danielang.elastic.productfacility.domain.ProductSale;
import org.mapstruct.*;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-23 18:19
 **/
@Mapper(componentModel = "cdi")
public interface ProductSaleMapper {
	ProductSaleDTO toDTO(ProductSale productSale);

	ProductSale toDomain(ProductSaleDTO productSaleDTO);

	ProductSale toDomain(ProductSaleEntity productSaleEntity);

	@Mapping(target = "pk", expression = "java(context.getPk())")
	ProductSaleEntity toEntity(ProductSale productSale, @Context ProductPKAndSK context);

	@AfterMapping
	default void setPKAndSK(@MappingTarget ProductSaleEntity target) {
		target.setSk(EntityUtil.SK_PREFIX + ProductSaleEntity.SK_SUFFIX);
	}
}
