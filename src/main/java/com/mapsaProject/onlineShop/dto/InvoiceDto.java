package com.mapsaProject.onlineShop.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data

public class InvoiceDto {


    private String descriptionDto;
    private boolean deliveredDto;

//    @ApiModelProperty(required = false, hidden = true)
//    private CartDto cartDto;
//    @ApiModelProperty(required = false, hidden = true)
//    private CustomerDto customerDto;

    @ApiModelProperty(required = false, hidden = true)
    private Long cartIdDto;
    @ApiModelProperty(required = false, hidden = true)
    private Long customerIdDto;


}
