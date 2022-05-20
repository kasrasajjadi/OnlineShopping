package com.mapsaProject.onlineShop.service;

import com.mapsaProject.onlineShop.model.Customer;
import com.mapsaProject.onlineShop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
     CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {

        return (List<Customer>) customerRepository.findAll();

    }

    @Override
    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);

    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {

            customer= optional.get();
        } else {
            throw new RuntimeException("customer not found id:" + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {

        customerRepository.deleteById(id);


    }


}
