package com.danielang.elastic.productfacility.convert;

import com.danielang.elastic.productfacility.controller.rest.dto.ProductInformationDTO;
import com.danielang.elastic.productfacility.db.entity.ProductInformationEntity;
import com.danielang.elastic.productfacility.db.utils.EntityUtil;
import com.danielang.elastic.productfacility.domain.ProductInformation;
import com.danielang.elastic.productfacility.domain.ProductPKAndSK;
import org.mapstruct.*;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-05-23 18:19
 **/
@Mapper(componentModel = "cdi")
public interface ProductInformationMapper {
	ProductInformationDTO toDTO(ProductInformation productInformation);

	ProductInformation toDomain(ProductInformationDTO productInformationDTO);

	ProductInformation toDomain(ProductInformationEntity productInformationEntity);

	@Mapping(target = "pk", expression = "java(context.getPk())")
	ProductInformationEntity toEntity(ProductInformation productInformation, @Context ProductPKAndSK context);

	@AfterMapping
	default void setPKAndSK(@MappingTarget ProductInformationEntity target) {
		target.setSk(EntityUtil.SK_PREFIX + ProductInformationEntity.SK_SUFFIX);
	}
}
