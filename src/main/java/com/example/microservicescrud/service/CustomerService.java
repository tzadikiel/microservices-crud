package com.example.microservicescrud.service;

import com.example.microservicescrud.view.CustomerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto getCustomer(String customerNumber);
    ResponseEntity<Void> validateCustomer(String email, String password);
}
