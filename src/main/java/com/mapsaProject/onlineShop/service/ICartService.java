package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Cart;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface ICartService {
    Cart save(Cart cart ,List<Long> products);
    Cart save(Cart cart );
    List<Cart> listAll();
    Optional<Cart> get(Long id);
    void delete(Long id);
    Page<Cart> getListPagination(int page, int size);

}

