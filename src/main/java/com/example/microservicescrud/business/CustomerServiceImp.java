package com.example.microservicescrud.business;

import com.example.microservicescrud.model.Customer;
import com.example.microservicescrud.repository.CustomerRepository;
import com.example.microservicescrud.service.CustomerService;
import com.example.microservicescrud.view.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final Function<Customer, CustomerDto> mapper = (c) -> new CustomerDto(c.getName(), c.getLastName(), c.getEmail());

    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomer(String customerNumber) {
        return mapper.apply(customerRepository.findById(customerNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Customer not found")));
    }

    @Override
    public ResponseEntity<Void> validateCustomer(String email, String password) {
        ResponseEntity<Void> entity;
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent() && customer.get().getPassword().equals(password) && !customer.get().getStatus().equals("B")) {
            customer.get().setSessionAlive(true);
            customerRepository.save(customer.get());
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", UUID.randomUUID().toString());
            entity = ResponseEntity.ok().headers(headers).build();
        } else {
            entity = ResponseEntity.unprocessableEntity().build();
        }
        return entity;
    }
}
