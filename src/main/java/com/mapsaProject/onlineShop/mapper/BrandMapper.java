package com.mapsaProject.onlineShop.mapper;

import com.mapsaProject.onlineShop.dto.BrandDto;
import com.mapsaProject.onlineShop.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface BrandMapper {



    //@Mapping(target = "brandIdDto" , source = "id")
    @Mapping(target = "titleDto" , source = "title")
    @Mapping(target = "descriptionDto" , source = "description")
    @Mapping(target = "imageDto" , source = "image")
    @Mapping(target = "productsDto", source = "products")
    BrandDto toBrandDto(Brand brand);
    //@Mapping(target = "id" , source = "brandIdDto")
    @Mapping(target = "title" , source = "titleDto")
    @Mapping(target =  "description", source ="descriptionDto" )
    @Mapping(target =  "image", source = "imageDto")
    @Mapping(target = "products", source = "productsDto")
    Brand toBrand(BrandDto brandDto);
    List<Brand> toBrandList(List<BrandDto> brandDtoList);


    List<BrandDto> toBrandDtoList(List<Brand> brandList);
}
