package com.mapsaProject.onlineShop.controller;


import com.mapsaProject.onlineShop.dto.CustomerDto;
import com.mapsaProject.onlineShop.dto.ProductImgDto;
import com.mapsaProject.onlineShop.exception.NotFoundException;
import com.mapsaProject.onlineShop.mapper.CustomerMapper;
import com.mapsaProject.onlineShop.model.Brand;
import com.mapsaProject.onlineShop.model.Customer;
import com.mapsaProject.onlineShop.model.Product;
import com.mapsaProject.onlineShop.model.ProductImg;
import com.mapsaProject.onlineShop.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(SampleLogerController.class);


  @Autowired
  CustomerMapper customerMapper;

  @Autowired
  ICustomerService customerService;


  @ApiOperation(value = "Add or insert customer")
  @ApiResponses(value = {
    @ApiResponse(code = 201 , message = "successfully added"),
    @ApiResponse(code = 401 , message = "You are not authorized"),
    @ApiResponse(code = 409 , message = "It is duplicate"),
    @ApiResponse(code = 500 , message = "server error"),
  })

  @GetMapping("/get/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {

    logger.info("get customer by id");
    Customer customer = customerService.getCustomerById(id);
    CustomerDto customerDto = customerMapper.toCustomerDTO(customer);
    return new ResponseEntity<>(customerDto, HttpStatus.OK);

  }

  @PostMapping("/save")
  public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {

    customerService.saveCustomer(customerMapper.toCustomer(customerDto));

    return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {

    Customer customer = customerService.getCustomerById(id);
    customerService.deleteCustomerById(customer.getId());

    return ResponseEntity.ok().body("customer with id:" + id + "deleted");
  }


  @GetMapping("/list")
  public List<CustomerDto> getAll() {
    return customerMapper.toCustomerDtoList(customerService.getAllCustomer());
  }



  @PutMapping("/update/{id}")
  ResponseEntity<String> updateCustomer(@PathVariable Long id,
                                        @RequestBody CustomerDto customerDto) {
    Customer customer = customerService.getCustomerById(id);
    Customer customer1= customerMapper.toCustomer(customerDto);
    customer1.setFirstName(customer.getFirstName());
    customer1.setLastName(customer.getLastName());
    customer1.setLocation(customer.getLocation());
    customer1.setMobile(customer.getMobile());
    customer1.setAge(customer.getAge());
    customerService.saveCustomer(customer1);
    return ResponseEntity.ok("updated");
  }

}
