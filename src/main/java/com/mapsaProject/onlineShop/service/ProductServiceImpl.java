package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
    @Override
    public List<Product> listAll(){
        return (List<Product>) productRepository.findAll();
    }
    @Override
    public Optional<Product> get(Long id){
        return productRepository.findById(id);
    }
    @Override
    public void delete(Product product ,Long id){
        get(product.getId())
                .orElseThrow(() -> new NotFoundException("No Product with this id" + id));
        productRepository.deleteById(id);
    }
    @Override
    public Product update(Product product, Long id){
        Product product1= get(product.getId())
                .orElseThrow(() -> new NotFoundException("No Product with this id" + id));

        product1.setId(product.getId());
        return product1;
    }
    @Override
    public Page<Product> getListPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("id")
        ));
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }
    @Override
    public Page<Product> getFilter(Double min, Double max){
        Pageable pageable = PageRequest.of(min.intValue(),  max.intValue());
        Page<Product> productPrice = productRepository.findAllByPriceBetween(min,max,pageable);
        return productPrice;
    }
    @Override
    public void asignBrandToProduct(Product product, Brand brand) {
        product.setBrand(brand);
        productRepository.save(product);
    }
    @Override
    public List<Product> similar(String string){
      return productRepository.findAllByTitleContains(string);
    }
}
