package com.mapsaProject.onlineShop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BrandDto {
    @ApiModelProperty(hidden = false,required = true)
    private String titleDto;

    @ApiModelProperty(hidden = false,required = true)
    private String descriptionDto;

    @ApiModelProperty(hidden = true)
    private String imageDto;

    @ApiModelProperty(hidden = true)
    private List<ProductDto> productsDto;
}
