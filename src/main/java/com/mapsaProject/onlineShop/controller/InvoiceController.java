package com.mapsaProject.onlineShop.controller;

import com.mapsaProject.onlineShop.dto.InvoiceDto;
import com.mapsaProject.onlineShop.dto.PageDto;
import com.mapsaProject.onlineShop.mapper.InvoiceMapper;
import com.mapsaProject.onlineShop.model.Invoice;
import com.mapsaProject.onlineShop.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    @Autowired
    IInvoiceService invoiceService;
    @Autowired
    InvoiceMapper invoiceMapper;


    @PostMapping(path = "/save-invoice/cart={cartId}customer={customerId}")
    public ResponseEntity<InvoiceDto> saveInvoice(
            @RequestBody InvoiceDto invoiceDto,
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "customerId") Long customerId) {
        invoiceDto.setCartIdDto(cartId);
        invoiceDto.setCustomerIdDto(customerId);
        Invoice invoice = invoiceMapper.dtoToInvoice(invoiceDto);
        Invoice savedInvoice = invoiceService.save(invoice);
        return new ResponseEntity<>(invoiceMapper.invoiceToDto(savedInvoice), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-invoice/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable(name = "id") Long id) {
        try {
            invoiceService.delete(id);
            return new ResponseEntity<>("The invoice has been deleted :)", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage() + " :( ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/find-invoice/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable(name = "id") Long id) {
        try {
            Invoice invoice = invoiceService.getById(id);
            InvoiceDto invoiceDto = invoiceMapper.invoiceToDto(invoice);
            return new ResponseEntity<>(invoiceDto, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/invoices-list")
    public ResponseEntity<List<InvoiceDto>> ListOfInvoices() {
        try {
            List<Invoice> invoiceList = invoiceService.getAll();
            List<InvoiceDto> invoiceDtoList = invoiceMapper.invoiceToDtoList(invoiceList);
            return new ResponseEntity<>(invoiceDtoList, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/page={page}-size={size}-sortBy={sortBy}")
    public ResponseEntity<PageDto>
    pagination(@PathVariable(name = "page") int page,
               @PathVariable(name = "size") int size,
               @PathVariable(name = "sortBy") String sortBy) throws Throwable {

        Page<Invoice> invoicePage = invoiceService.findAllInPage(page, size, sortBy);
        PageDto pageDto = new PageDto();
        pageDto.setTotalPageDto(invoicePage.getTotalPages());
        pageDto.setPageSizeDto(invoicePage.getSize());
        pageDto.setTotalListDto(invoicePage.getNumberOfElements());
        pageDto.setListDto(invoiceMapper.invoiceToDtoList(invoicePage.toList()));
        pageDto.setCurrentPageDto(page);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @PutMapping(path = "/update-record-no{id}")
    public ResponseEntity<InvoiceDto>
    updateRecord(@PathVariable(name = "id") Long id,
                 @RequestBody InvoiceDto invoiceDto) throws Throwable {
        Invoice invoice = invoiceMapper.dtoToInvoice(invoiceDto);
        InvoiceDto updatedDto = invoiceMapper.invoiceToDto(invoiceService.update(id, invoice));
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @GetMapping(path = "/find-by-customerId={customerId}")
    public ResponseEntity<List<InvoiceDto>> findByCustomerId(
            @PathVariable(name = "customerId") Long customerId) {

        List<Invoice> invoiceList = invoiceService.findInvoicesByCustomerId(customerId);
        List<InvoiceDto> invoiceDtoList = invoiceMapper.invoiceToDtoList(invoiceList);
        return new ResponseEntity<>(invoiceDtoList, HttpStatus.OK);
    }
}
