package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.model.ProductImg;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface IProductImgService {

    ProductImg save(MultipartFile file) throws IOException;

    ProductImg update(MultipartFile file, Long id) throws IOException;

    void delete(Long id);

    List<ProductImg> listAll();

    Optional<ProductImg> get(Long id);


    Resource download(String filename);

    Page<ProductImg> getListPagination(int page, int size);

    public String saveCategory (MultipartFile file)throws IOException;

    public String saveBrand (MultipartFile file)throws IOException;

    public String saveProduct(MultipartFile file , Product product) throws IOException;
}
