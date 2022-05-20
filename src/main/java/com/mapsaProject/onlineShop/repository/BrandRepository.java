package com.mapsaProject.onlineShop.repository;

import com.mapsaProject.onlineShop.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand,Long> {


    Page<Brand> findAll(Pageable pageable);
    List<Brand> findAllByTitleContains(String title)   ;
}
