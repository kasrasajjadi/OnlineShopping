package com.mapsaProject.onlineShop.repository;

import com.mapsaProject.onlineShop.model.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice,Long> {
    List<Invoice> findAllByCustomerId(Long id);
}
