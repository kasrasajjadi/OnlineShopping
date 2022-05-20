package com.mapsaProject.onlineShop.dto;

import com.mapsaProject.onlineShop.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Setter
@Getter
public class ProductImgDto {

    private Long productImgIdDto;

    private String productImagesDto;

    private Product productDto;

    @Transient
    public String getPhotosImagePath() {
        if (productImagesDto == null || productImgIdDto == null)
            return null;

        return "/upload/" + productImgIdDto + "/" + productImagesDto;
    }

}
