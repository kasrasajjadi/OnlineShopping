package com.mapsaProject.onlineShop.controller;

import com.mapsaProject.onlineShop.dto.BrandDto;
import com.mapsaProject.onlineShop.dto.PageDto;
import com.mapsaProject.onlineShop.dto.ProductDto;
import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.BrandMapper;
import com.mapsaProject.onlineShop.mapper.ProductMapper;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.service.IBrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

//TODO please add static url to RequestMapping and remove it from methods
@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BrandController {

    private  final IBrandService brandService;
    private final BrandMapper brandMapper;
    private  final ProductMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger(SampleLogerController.class);

    //Create from crud
    @ApiOperation(value = "list of brands")
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @GetMapping(value = "/list")
    public ResponseEntity<List<BrandDto>> list(){
        logger.info(" listed brands");
        return new ResponseEntity<>(brandMapper.toBrandDtoList(brandService.listAll()),HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @PostMapping("/product/{brandId}")
    public ResponseEntity<String> addToBrand(@RequestBody List<Long> ids,@PathVariable("brandId") Long brandId){
        Brand brand = brandService.get(brandId)
                .orElseThrow(() -> new NotFoundException("No Brand with this id" + brandId));
        brandService.save(brand, ids);
        logger.info("The product added to Brand");
        return new ResponseEntity<>("added to Brand successfully", HttpStatus.OK);

    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @GetMapping(value="/get/{id}")
    ResponseEntity<Brand> getById(@PathVariable("id") Long id) {
        Brand brand = brandService.get(id)
                .orElseThrow(()->new NotFoundException("No Brand with ID : "+id));
        return ResponseEntity.ok().body(brand);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @GetMapping(value = "/pagination/{page}/{size}")
    public ResponseEntity<PageDto> pagination(@PathVariable("page")int page,@PathVariable("size")int size) {
        Page<Brand> pagination = brandService.getListPagination(page,size);
        PageDto pageDto=new PageDto();
        pageDto.setListDto(pagination.toList());
        pageDto.setTotalListDto(pagination.getTotalPages());
        pageDto.setPageSizeDto(page);
        return new ResponseEntity<>(pageDto,HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @GetMapping(value = "/list/{brandId}")
    public ResponseEntity< List<ProductDto>> productDtoList(@PathVariable Long brandId) {
        Brand brand=brandService.get(brandId)
                .orElseThrow(()->new NotFoundException("No Brand with ID : "+brandId));
        List<Product> products=brand .getProducts();
        return new ResponseEntity<>(productMapper.toProductDtoList(products),HttpStatus.OK);

    }
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "successfully added"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @PostMapping("")
    public ResponseEntity add(@RequestBody BrandDto brandDto) {
        Brand brand=brandMapper.toBrand(brandDto);
        brandService.add(brand);
        return new ResponseEntity<>("added to Brand successfully", HttpStatus.CREATED);

    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "successfully added"),
            @ApiResponse(code = 404 , message = " not found"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody BrandDto brandDto, @PathVariable Long id) {
        try {

            Brand brand = brandMapper.toBrand(brandDto);

            brandService.update(id,brand);
            return new ResponseEntity<>("Brand Updated", HttpStatus.OK);
        }catch (NoSuchElementException e){
            logger.error("Unsuccessful operation");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @ApiResponses(value = {
            @ApiResponse(code = 202 , message = " accepted"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete (@PathVariable Long id){
        Brand brand=brandService.get(id) .orElseThrow(()->new NotFoundException("No Brand with ID : "+id));
        brandService.delete(brand.getId());
        return ResponseEntity.accepted().body("Brand image with ID : "+id+" deleted with success!");

    }
    @ApiResponses(value = {
            @ApiResponse(code = 202 , message = " accepted"),
            @ApiResponse(code = 500 , message = "server error"),
    })
    @GetMapping(value = "/search/")
    public ResponseEntity< List<BrandDto>> searchByName(@RequestBody String title) {
        List<Brand> brands=brandService.searchByName(title)  ;
        return new ResponseEntity<>(brandMapper.toBrandDtoList(brands),HttpStatus.OK);

    }


}

