package com.danielang.elastic.productfacility.convert;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductDTO;
import com.danielang.elastic.productfacility.db.entity.DynamoDBEntity;
import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.domain.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-23 18:19
 **/
@Mapper(componentModel = "cdi")
public interface ProductMapper {
	ProductDTO toDTO(Product product);

	Product toDomain(ProductDTO productDTO);

	ProductEntity toEntity(Product product);

	@AfterMapping
	default void setPKAndSK(@MappingTarget ProductEntity target, Product source) {
		target.setPk(String.format("%s#%s", source.getInsuranceTenant(), source.getProductCode()));
		target.setSk(DynamoDBEntity.SK_PREFIX + ProductEntity.SK_SUFFIX);
	}
}
