package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.model.Cart;
import com.mapsaProject.onlineShop.model.Customer;
import com.mapsaProject.onlineShop.repository.CartRepository;
import com.mapsaProject.onlineShop.repository.CustomerRepository;
import com.mapsaProject.onlineShop.repository.InvoiceRepository;
import com.mapsaProject.onlineShop.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;


    @Autowired
    CartService cartService;
    @Autowired
    CustomerServiceImpl customerService;


    @Override
    public Invoice save(Invoice invoice) {
        Optional<Cart> cart = cartService.get(invoice.getCartId());
        if (!cart.isPresent()) {
            throw new NotFoundException("There is no such cart ID saved in database!");
        }
        customerService.getCustomerById(invoice.getCustomerId());
        return invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice getById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        } else {
            throw new NotFoundException("Invoice corresponding to the given Id is not found!");
        }
    }

    @Override
    public List<Invoice> getAll() {
        Iterable<Invoice> iterable = invoiceRepository.findAll();
        ArrayList<Invoice> listOfInvoice = new ArrayList<>();
        for (Invoice invoice : iterable) {
            listOfInvoice.add(invoice);
        }
        return listOfInvoice;
    }

    @Override
    public Page<Invoice> findAllInPage(int pageNumber, int size, String sortBy) throws Throwable {
        try {
            Pageable pageableSorted = PageRequest.of(pageNumber, size, Sort.by(sortBy));
            Page<Invoice> invoicePage = invoiceRepository.findAll(pageableSorted);
            return invoicePage;
        } catch (Exception exception) {
            throw new Throwable("There was a problem in paging the invoices");
        }

    }


    @Override
    public Invoice update(Long id, Invoice invoice) {
        Invoice foundInvoice = getById(id);
        foundInvoice.setDelivered(invoice.isDelivered());
        foundInvoice.setDescription(invoice.getDescription());
        foundInvoice.setUpdatedAt(new Date());
        foundInvoice.setDeleted(invoice.isDeleted());
        return save(foundInvoice);
    }

    @Override
    public List<Invoice> findInvoicesByCustomerId(Long id) {
        customerService.getCustomerById(id);
        try {
            return invoiceRepository.findAllByCustomerId(id);
        } catch (Exception exception ) {
            throw new NotFoundException("there is no invoice corresponding to that customer ID");
        }

    }

}
