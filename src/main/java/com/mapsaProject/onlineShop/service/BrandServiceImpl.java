package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.BrandMapper;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    BrandRepository brandRepo;
    @Autowired
    IProductService productService;
    @Autowired
    BrandMapper brandMapper;

    @Override
    public void save(Brand brand,List<Long>  productId) {


        List<Product> productList = new ArrayList<>();
        for (Long id : productId) {
            Optional<Product> optionalProduct = productService.get(id);
            if (!optionalProduct.isPresent()) {
                throw new NotFoundException("not found such product" + productId);
            } else {
                productService.asignBrandToProduct(optionalProduct.get(),brand);

            }
        }

    }


    @Override
    public List<Brand> listAll() {
        return (List<Brand>) brandRepo.findAll();
    }

    public Optional<Brand> get(Long id) {
        return brandRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        brandRepo.deleteById(id);
    }

    @Override
    public Page<Brand> getListPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        Page<Brand> brandPage = brandRepo.findAll(pageable);
        return brandPage;
    }

    @Override
    public void add(Brand brand) {
        brandRepo.save(brand);
    }


    @Override
    public void update(Long id, Brand brandD) {
        Brand existBrand = brandRepo.findById(id).orElseThrow(() -> new NotFoundException("No Brand with ID : " + id));
        brandD.setId(existBrand.getId());
        add(brandD);
    }

    @Override
    public List<Brand> searchByName(String title) {
        return brandRepo.findAllByTitleContains(title);
    }

}
