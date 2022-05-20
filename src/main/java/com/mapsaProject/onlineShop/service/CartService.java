package com.mapsaProject.onlineShop.service;


import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.model.Cart;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    IProductService productService;
    @Transactional
    @Override
    public Cart save(Cart cart ,List<Long> products){
        Cart cart1=new Cart();
        List<Product>  productList=new ArrayList();
        for(Long id:products)
        {
            Optional<Product> optionalProduct=productService.get(id);
            if (optionalProduct.isPresent())
            {
                cart1.setProductList(productList);
                productList.add(optionalProduct.get());
            }
            else{
                throw new NotFoundException("product not found id:" + id);
            }
        }
        cart1.setProductList(productList);
        cart1.setTotalPrice(cart.getTotalPrice());
        cart1.setNumberOfProduct(cart.getNumberOfProduct());
        cart1.setCreatedAt(cart.getCreatedAt());
        cart1.setId(cart.getId());
        return cartRepository.save(cart1);
    }

    @Override
    public Cart save(Cart cart) {
      return   cartRepository.save(cart);

    }

    @Override
    public List<Cart> listAll() {
        return (List<Cart>) cartRepository.findAll();
    }
    @Override
    public Optional<Cart> get(Long id) { return cartRepository.findById(id);
    }

    
    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
    public Page<Cart> getListPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(
                Sort.Order.desc("id")
        ));
        Page<Cart> cartPage = cartRepository.findAll(pageable);
        return cartPage;
    }

    }


