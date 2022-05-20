package com.mapsaProject.onlineShop.repository;

import com.mapsaProject.onlineShop.model.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
}
