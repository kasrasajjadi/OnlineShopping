package com.mapsaProject.onlineShop.controller;

import com.mapsaProject.onlineShop.dto.CartDto;
import com.mapsaProject.onlineShop.dto.PageDto;
import com.mapsaProject.onlineShop.dto.ProductDto;
import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.CartMapper;
import com.mapsaProject.onlineShop.mapper.ProductMapper;
import com.mapsaProject.onlineShop.model.Cart;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.service.ICartService;
import com.mapsaProject.onlineShop.service.IProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor(onConstructor_ =@Autowired )
public class CartController {

    @Autowired
    ICartService iCartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;

    @ApiOperation(value = "Add or insert Cart ")
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "successfully added"),
            @ApiResponse(code = 401 , message = "You are not authorized"),
            @ApiResponse(code = 409 , message = "It is duplicate"),
            @ApiResponse(code = 500 , message = "server error"),
    })

    @GetMapping(value = "/list")
    public List<CartDto> list() {
        return  cartMapper.toCartDtoList(iCartService.listAll());
    }

    @GetMapping(value="/get/{id}")
    ResponseEntity<Cart> getById(@PathVariable("id") Long id) {
        Cart cart = iCartService.get(id)
                .orElseThrow(()->new NotFoundException("No Cart with ID : "+id));
        return ResponseEntity.ok().body(cart);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDto){

        Cart cart = cartMapper.toCart(cartDto );
        Cart addCart = iCartService.save(cart ,cartDto.getProducts());
        return new ResponseEntity("cart added",HttpStatus.OK);
    }
    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<ProductDto> pagination(@PathVariable("page") int page, @PathVariable("size") int size){

        Page<Cart> pagination = iCartService.getListPagination(page,size);
        PageDto pageDto=new PageDto();
        pageDto.setListDto(pagination.toList());
        pageDto.setTotalListDto(pagination.getTotalPages());
        pageDto.setPageSizeDto(page);
        return new  ResponseEntity("pagination works!", HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<Cart> updateCart(@PathVariable("id") Long id,@RequestBody CartDto cartDto) {
        Cart cart =iCartService.get(id)
                .orElseThrow(()->new NotFoundException("No cart with ID : "+id));

        Cart toCart = cartMapper.toCart(cartDto);
        toCart.setId(cart.getId());
        iCartService.save(toCart);
        return ResponseEntity.ok().body(toCart);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteCart( @PathVariable("id") Long id) {

        Cart cart = iCartService.get(id)
                .orElseThrow(()->new NotFoundException("No cart with ID : "+id));

        iCartService.delete(cart.getId());

        return ResponseEntity.ok().body("cart  with ID : "+id+" deleted with success!");
    }

}
