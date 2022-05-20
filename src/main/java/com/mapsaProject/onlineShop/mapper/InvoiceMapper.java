package com.mapsaProject.onlineShop.mapper;

import com.mapsaProject.onlineShop.dto.InvoiceDto;
import com.mapsaProject.onlineShop.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "descriptionDto", source = "description")
    @Mapping(target = "deliveredDto", source = "delivered")
    @Mapping(target = "cartIdDto", source = "cartId")
    @Mapping(target = "customerIdDto", source = "customerId")
//    @Mapping(target = "cartDto", source = "cart")
//    @Mapping(target = "customerDto", source = "customer")
    InvoiceDto invoiceToDto(Invoice invoice);


    @Mapping(target = "description", source = "descriptionDto")
    @Mapping(target = "delivered", source = "deliveredDto")
    @Mapping(target = "cartId", source = "cartIdDto")
    @Mapping(target = "customerId", source = "customerIdDto")
//    @Mapping(target = "cart", source = "cartDto")
//    @Mapping(target = "customer", source = "customerDto")
    Invoice dtoToInvoice(InvoiceDto invoiceDto);

    List<InvoiceDto> invoiceToDtoList(List<Invoice> invoiceList);

    List<Invoice> dtoToInvoiceList(List<InvoiceDto> invoiceDtoList);

}
