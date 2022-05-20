package com.mapsaProject.onlineShop.service;
import com.mapsaProject.onlineShop.model.Customer;

import java.util.List;

public interface ICustomerService {


    List<Customer> getAllCustomer();

    void saveCustomer(Customer customer);

    Customer getCustomerById(long id);

    void deleteCustomerById(long id);

}
