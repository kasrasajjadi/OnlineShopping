package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Invoice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInvoiceService {
    Invoice save(Invoice invoice);
    void delete(Long id);
    Invoice getById (Long id);
    List<Invoice> getAll();
    Page<Invoice> findAllInPage(int pageNumber, int size, String sortBy) throws Throwable;
    Invoice update(Long id, Invoice invoice) throws Throwable;
    List<Invoice> findInvoicesByCustomerId(Long id);
}
