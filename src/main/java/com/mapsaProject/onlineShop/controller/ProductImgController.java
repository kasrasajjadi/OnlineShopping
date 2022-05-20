package com.mapsaProject.onlineShop.controller;

import com.mapsaProject.onlineShop.dto.BrandDto;
import com.mapsaProject.onlineShop.dto.PageDto;
import com.mapsaProject.onlineShop.dto.ProductImgDto;
import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.ProductImgMapper;
//import com.mapsaProject.onlineShop.mapper.ProductImgMapperImpl;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.ProductImg;
import com.mapsaProject.onlineShop.service.IProductImgService;
import com.mapsaProject.onlineShop.util.FileUploadUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-image")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductImgController {

    private final IProductImgService IProductImgService;
    private final ProductImgMapper productImgMapper;
    private static final Logger logger = LoggerFactory.getLogger(SampleLogerController.class);

    //Create from crud
    @ApiOperation(value = "Add or insert Product image")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 409, message = "It is duplicate"),
            @ApiResponse(code = 500, message = "server error"),
    })

    //Create from crud-save
    @PostMapping(value="/save")
    public ResponseEntity<String> addProductImg(@RequestBody MultipartFile file) throws IOException {

        ProductImg addedProductImg = IProductImgService.save(file);

        logger.info("saved!");

        return ResponseEntity.ok().body("Saved successfully! " +
                "The name of uploaded image is : " + addedProductImg.getProductImages());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        // Load file as Resource
        Resource resource = IProductImgService.download(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //Read from crud --> getAll
    @GetMapping("/list")
    public List<ProductImgDto> getAll() {
        logger.info("found all!");
        return productImgMapper.toProductImgDtoList(IProductImgService.listAll());
    }



    //Read from crud --> get one
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ProductImg> getById(@PathVariable("id") Long id) {

        ProductImg productImg = IProductImgService.get(id)
                .orElseThrow(() -> new NotFoundException("No Product image with ID : " + id));

        logger.info("found!");

        return ResponseEntity.ok().body(productImg);
    }


    //Update from crud
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProductImg(@PathVariable("id") Long id,
                                                   @RequestBody MultipartFile file) throws IOException {

        ProductImg productImg = IProductImgService.update(file, id);

        //logger.info("updated!");

        return ResponseEntity.ok().body("Updated successfully! " +
                "The name of uploaded image is : " + productImg.getProductImages());
    }

    //Delete from crud
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {

        IProductImgService.delete(id);

        logger.info("deleted!");

        return ResponseEntity.ok().body("Product image with ID : " + id + " deleted with success!");
    }

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<ProductImgDto> pagination(@PathVariable("page")int page, @PathVariable("size")int size) {
        Page<ProductImg> pagination = IProductImgService.getListPagination(page,size);
        PageDto pageDto=new PageDto();
        pageDto.setTotalListDto(pagination.getTotalPages());
        pageDto.setPageSizeDto(size);
        pageDto.setListDto(pagination.toList());

        return  new ResponseEntity("pagination works fine!",HttpStatus.OK);
    }
}
