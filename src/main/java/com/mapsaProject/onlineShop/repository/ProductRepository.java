package com.mapsaProject.onlineShop.repository;

import com.mapsaProject.onlineShop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByPriceBetween(Double min, Double max, Pageable pageable);

    List<Product> findAllByTitleContains(String string);
}
