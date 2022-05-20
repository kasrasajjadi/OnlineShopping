package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product save(Product product);
    List<Product> listAll();
    Optional<Product> get(Long id);
    void delete(Product product, Long id);
    Page<Product> getListPagination(int page, int size);
    Page<Product> getFilter(Double min, Double max);
    void asignBrandToProduct(Product product, Brand brand);
    Product update(Product product, Long id);
    List<Product> similar(String string);

}
