package com.example.microservicescrud.business;

import com.example.microservicescrud.model.Credentials;
import com.example.microservicescrud.model.Customer;
import com.example.microservicescrud.repository.CredentialsRepository;
import com.example.microservicescrud.repository.CustomerRepository;
import com.example.microservicescrud.service.CredentialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CredentialsServiceImp implements CredentialsService {

    @Autowired
    private CredentialsRepository credRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CredentialsServiceImp.class);

    @Override
    public boolean getLogin(Credentials credentials) {
        Optional<Credentials> cred = credRepository.findById(credentials.getCustomerNumber());
        Customer customer;
        boolean valide = false;
        LOG.info("Customer Number: {}", cred.get().getCustomerNumber());
        LOG.info("Password: {}", cred.get().getPassword());
        if (cred.get().getCustomerNumber().equals(credentials.getCustomerNumber())
                && cred.get().getPassword().equals(credentials.getPassword())) {
            customer = customerRepository.getById(credentials.getCustomerNumber());
            if (customer.getStatus().equals("A")) {
                customer.setSessionAlive(true);
                customerRepository.save(customer);
                valide = true;
            }
        }
        return valide;
    }
}
