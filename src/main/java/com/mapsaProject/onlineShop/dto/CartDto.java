package com.mapsaProject.onlineShop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CartDto {

    private Double totalPriceDTO;

    private Long numberOfProductDTO;
    @ApiModelProperty(hidden = false ,required = true)
    private List<ProductDto> productListDTO;
    private List<Long> products;

}
