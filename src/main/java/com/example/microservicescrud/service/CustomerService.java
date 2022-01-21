package com.example.microservicescrud.service;

import com.example.microservicescrud.view.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto getCustomer(String customerNumber);
}
