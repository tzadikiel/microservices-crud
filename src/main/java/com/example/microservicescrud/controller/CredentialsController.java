package com.example.microservicescrud.controller;

import com.example.microservicescrud.model.Credentials;
import com.example.microservicescrud.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Credentials credentials) {
        if (credentialsService.getLogin(credentials)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Authorization", UUID.randomUUID().toString());
            return ResponseEntity.ok().headers(headers).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
