package com.mapsaProject.onlineShop.dto;

import com.mapsaProject.onlineShop.model.Cart;
import com.mapsaProject.onlineShop.model.ProductImg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    @ApiModelProperty(hidden = false, required = true)
    private String titleDto;
    @ApiModelProperty(hidden = false, required = true)
    private Double priceDto;
    @ApiModelProperty(hidden = true)
    private String descriptionDto;
    @ApiModelProperty(hidden = true)
    private List<ProductImg> productImgDto;
    @ApiModelProperty(hidden = true)
    private Cart cartDto;
}
