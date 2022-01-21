package com.example.microservicescrud.repository;

import com.example.microservicescrud.model.Credentials;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<Credentials, String> {

}
