package com.mapsaProject.onlineShop.mapper;

import com.mapsaProject.onlineShop.dto.CustomerDto;
import com.mapsaProject.onlineShop.dto.ProductImgDto;
import com.mapsaProject.onlineShop.model.Customer;

import com.mapsaProject.onlineShop.model.ProductImg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "firstNameDto", source = "firstName")
    @Mapping(target = "lastNameDto", source = "lastName")
    @Mapping(target = "locationDto", source = "location")
    @Mapping(target = "mobileDto", source = "mobile")
    CustomerDto toCustomerDTO(Customer customer);

    List<CustomerDto> toCustomerDtoList(List<Customer> customerList);


    @Mapping(target = "firstName", source = "firstNameDto")
    @Mapping(target = "lastName", source = "lastNameDto")
    @Mapping(target = "location", source = "locationDto")
    @Mapping(target = "mobile", source = "mobileDto")
    Customer toCustomer(CustomerDto customerDTO);

    List<Customer> toCustomerList(List<CustomerDto> customerDtoList);

}
