package com.danielang.elastic.productfacility.convert;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductDTO;
import com.danielang.elastic.productfacility.db.entity.ProductEntity;
import com.danielang.elastic.productfacility.db.utils.EntityUtil;
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

	Product toDomain(ProductEntity productDTO);

	ProductEntity toEntity(Product product);

	@AfterMapping
	default void setPKAndSK(@MappingTarget ProductEntity target, Product source) {
		target.setPk(String.format("%s#%s", source.getInsuranceTenant(), source.getProductCode()));
		target.setSk(EntityUtil.SK_PREFIX + ProductEntity.SK_SUFFIX);
	}

	@AfterMapping
	default void parsePKAndSK(@MappingTarget Product target, ProductEntity source) {
		String[] pkInfo = source.getPk().split("#");
		target.setInsuranceTenant(pkInfo[0]);
		target.setProductCode(pkInfo[1]);
	}
}
