package com.mapsaProject.onlineShop.mapper;

import com.mapsaProject.onlineShop.dto.ProductImgDto;
import com.mapsaProject.onlineShop.model.ProductImg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImgMapper {

    @Mapping(target = "productImgIdDto", source = "id")
    @Mapping(target = "productImagesDto", source = "productImages")
    @Mapping(target = "productDto", source = "product")
    ProductImgDto toProductImgDto(ProductImg productImg);

    List<ProductImgDto> toProductImgDtoList(List<ProductImg> productImgList);

    @Mapping(target = "id" , source = "productImgIdDto")
    @Mapping(target = "productImages" , source = "productImagesDto" )
    @Mapping(target = "product" , source = "productDto" )
    ProductImg toProductImg(ProductImgDto productImgDto);

    List<ProductImg> toProductImgList(List<ProductImgDto> productImgDtoList);
}
