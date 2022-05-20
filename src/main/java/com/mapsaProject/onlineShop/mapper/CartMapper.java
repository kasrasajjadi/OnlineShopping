package com.mapsaProject.onlineShop.mapper;
import com.mapsaProject.onlineShop.dto.CartDto;
import com.mapsaProject.onlineShop.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface CartMapper {

    @Mapping(target = "numberOfProductDTO" , source = "numberOfProduct")
    @Mapping(target = "totalPriceDTO" , source = "totalPrice")
    @Mapping(target = "productListDTO" , source = "productList")
    CartDto toCartDTO(Cart cart);
    List<CartDto> toCartDtoList(List<Cart> productImgList);
    @Mapping(target = "numberOfProduct" , source = "numberOfProductDTO")
    @Mapping(target = "totalPrice" , source = "totalPriceDTO")
    @Mapping(target = "productList" , source = "productListDTO")
    Cart toCart(CartDto cartDTO);
    List<Cart> toCartList(List<CartDto> cartDtoList);
}