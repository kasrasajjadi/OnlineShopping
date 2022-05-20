package com.mapsaProject.onlineShop.repository;

import com.mapsaProject.onlineShop.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {



}
