package com.example.microservicescrud.controller;

import com.example.microservicescrud.model.Customer;
import com.example.microservicescrud.service.CustomerService;
import com.example.microservicescrud.view.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerNumber}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerNumber") String customerNumber) {
        return new ResponseEntity<>(customerService.getCustomer(customerNumber), HttpStatus.OK);
    }

    @GetMapping("/customers/check")
    public ResponseEntity<Void> ckeckUser (@PathParam("email") String email, @PathParam("password") String password) {
        return customerService.validateCustomer(email, password);
    }

}
