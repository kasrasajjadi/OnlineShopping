package com.mapsaProject.onlineShop.controller;

import com.mapsaProject.onlineShop.dto.PageDto;
import com.mapsaProject.onlineShop.dto.ProductDto;
import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.ProductMapper;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.service.ICartService;
import com.mapsaProject.onlineShop.service.IProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    IProductService productService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ICartService cartService;

    @ApiOperation(value = "product List")
    @ApiResponses(value = {
            @ApiResponse(code=401, message="You are not authorized"),
            @ApiResponse(code=500, message="server error")
    })
    @GetMapping(value = "/list")
    public ResponseEntity<List<ProductDto>> list(){
        logger.info("Customer listed products");
        return new ResponseEntity<>(productMapper.toProductDtoList(productService.listAll()), HttpStatus.OK);
    }

    @ApiOperation(value = "get product by id")
    @ApiResponses(value = {
            @ApiResponse(code=404, message="Not Found!"),
            @ApiResponse(code=500, message="server error")
    })
   @GetMapping(value = "/get/{id}")
   public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id){
        Product product = productService.get(id)
                .orElseThrow(() -> new NotFoundException("No Product with this ID" + id));
        logger.info("Customer got one product by its id");
        return new ResponseEntity(productMapper.toProductDto(product), HttpStatus.OK);
   }

    @ApiOperation(value = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(code=201, message="successfully added"),
            @ApiResponse(code=401, message="You are not authorized to add new product"),
            @ApiResponse(code=500, message="server error")
    })
    @PostMapping(value = "/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){

       Product product3 = productMapper.toProduct(productDto);
       Product addedProduct = productService.save(product3);
       logger.info("Customer added the product");
       return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @ApiOperation(value = "Update product information")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="ok"),
            @ApiResponse(code=401, message="You are not authorized to update any product"),
            @ApiResponse(code=500, message="server error")
    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id ){
        try {

            Product product = productMapper.toProduct(productDto);
            productService.save(productService.update(product,id));
            logger.info("products updated");
            return new ResponseEntity("Product Updated", HttpStatus.OK);

        }catch (NoSuchElementException e){
            logger.error("Unsuccessful operation");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "delete products")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="successfully deleted"),
            @ApiResponse(code=401, message="You are not authorized to delete any product"),
            @ApiResponse(code=500, message="server error")
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@RequestBody ProductDto productDto,@PathVariable("id") Long id){
        Product product = productMapper.toProduct(productDto);
        productService.delete(product, id);
        logger.info("product deleted");
        return ResponseEntity.ok().body("Product with id" + id + "deleted successfully");
    }

    @ApiOperation(value = "pagination")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="ok"),
            @ApiResponse(code=500, message="server error")
    })
    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<ProductDto> pagination(@PathVariable("page") int page, @PathVariable("size") int size){

        Page<Product> pagination = productService.getListPagination(page,size);
        PageDto pageDto=new PageDto();
        pageDto.setListDto(pagination.toList());
        pageDto.setTotalListDto(pagination.getTotalPages());
        pageDto.setPageSizeDto(page);
        logger.info("Pagination Approved");
        return new  ResponseEntity(pagination, HttpStatus.OK);
    }

    @ApiOperation(value = "Filter by Price")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="ok"),
            @ApiResponse(code=204, message="No content"),
            @ApiResponse(code=500, message="server error")
    })
    @GetMapping(value = "/filtering/{min}/{max}/{page}/{size}")
    public ResponseEntity<ProductDto> PaginatedFiltering(@PathVariable("min") Double min, @PathVariable("max") Double max){
        Page<Product> productPage = productService.getFilter(min,max);
        logger.info("The price is filtered by a range");
        return new ResponseEntity(productPage, HttpStatus.OK);
    }

    @ApiOperation(value = "Products with similar name")
    @ApiResponses(value = {
            @ApiResponse(code=200, message="ok"),
            @ApiResponse(code=204, message="No content"),
            @ApiResponse(code=500, message="server error")
    })
    @PostMapping(value = "/similarity")
    public ResponseEntity<ProductDto> similarity(@RequestBody String string){
       return new ResponseEntity(productService.similar(string), HttpStatus.OK);
    }

}
