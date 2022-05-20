package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBrandService {

    void save(Brand brand, List<Long> ids);
    List<Brand> listAll();
    Optional<Brand> get(Long id);
    void delete(Long id);
    Page<Brand>getListPagination(int page,int size);
    void add(Brand brand);


    void update(Long id, Brand brandD);

    List<Brand> searchByName(String title);
}
