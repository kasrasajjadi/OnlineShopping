package com.mapsaProject.onlineShop.mapper;

import com.mapsaProject.onlineShop.dto.ProductDto;
import com.mapsaProject.onlineShop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductDto.class )
public interface ProductMapper {

    @Mapping(target = "titleDto", source = "title")
    @Mapping(target = "descriptionDto", source = "description")
    @Mapping(target = "priceDto", source = "price")
    @Mapping(target = "productImgDto", source = "productImg")
    @Mapping(target = "cartDto", source = "cart")
    ProductDto toProductDto(Product product);

    @Mapping(source = "titleDto", target = "title")
    @Mapping(source = "descriptionDto", target = "description")
    @Mapping(source = "priceDto", target = "price")
    @Mapping(source = "productImgDto", target = "productImg")
    @Mapping(source = "cartDto", target = "cart")
    Product toProduct(ProductDto productDto);
    
    List<Product> toProductList(List<ProductDto> productDtoList);

    List<ProductDto> toProductDtoList(List<Product> productList);

}
