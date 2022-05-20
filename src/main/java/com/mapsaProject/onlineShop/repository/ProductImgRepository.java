package com.mapsaProject.onlineShop.repository;


import com.mapsaProject.onlineShop.model.ProductImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends PagingAndSortingRepository <ProductImg,Long> {

    Page<ProductImg> findAll(Pageable pageable);
}
