package com.mapsaProject.onlineShop.service;


import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.model.ProductImg;
import com.mapsaProject.onlineShop.repository.ProductImgRepository;
import com.mapsaProject.onlineShop.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductImgServiceImpl implements IProductImgService {


    @Autowired
    ProductImgRepository productImgRepository;

    @Autowired
    FileUploadUtil fileUploadUtil;


    @Transactional
    @Override
    public ProductImg save(MultipartFile file) throws IOException {

        //saving the file
        String uploadedImageName = fileUploadUtil.fileUpload(file);
        ProductImg productImg = new ProductImg();
        productImg.setProductImages(uploadedImageName);

        return productImgRepository.save(productImg);
    }

    @Override
    public ProductImg update(MultipartFile file, Long id) throws IOException {


        ProductImg productImg = productImgRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No Product with ID : "+id));

        //delete productImg and file
        boolean isDeleted = fileUploadUtil.deleteFile(productImg.getProductImages());


        if(isDeleted){

            ProductImg productImg2 = new ProductImg();

            productImg2.setId(productImg.getId());

            String uploadedImageName = fileUploadUtil.fileUpload(file);

            productImg2.setProductImages(uploadedImageName);

            return productImgRepository.save(productImg2);
        }

        throw new NotFoundException("image is not found!");
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productImgRepository.deleteById(id);
    }

    @Override
    public List<ProductImg> listAll() {
        return (List<ProductImg>) productImgRepository.findAll();
    }

    @Override
    public Optional<ProductImg> get(Long id) {
       return productImgRepository.findById(id);
    }

    @Override
    public Resource download(String filename) {

        return fileUploadUtil.loadFile(filename);
    }

    @Override
    public Page<ProductImg> getListPagination(int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by(Sort.Order.desc("id"))) ;
        Page<ProductImg> productImgPage=productImgRepository.findAll(pageable);
        return productImgPage;
    }

    @Override
    public String saveCategory(MultipartFile file) throws IOException {

        ProductImgServiceImpl productImgService =new ProductImgServiceImpl();

        ProductImg productImg = productImgService.save(file);


        return productImg.getProductImages();
    }

    @Override
    public String saveBrand(MultipartFile file) throws IOException {
        ProductImgServiceImpl productImgService =new ProductImgServiceImpl();

        ProductImg productImg = productImgService.save(file);


        return productImg.getProductImages();
    }

    @Override
    public String saveProduct(MultipartFile file, Product product) throws IOException {

        //saving the file
        String uploadedImageName = fileUploadUtil.fileUpload(file);
        ProductImg productImg = new ProductImg();
        productImg.setProductImages(uploadedImageName);
        productImg.setProduct(product);

        return productImgRepository.save(productImg).getProductImages();
    }


}
