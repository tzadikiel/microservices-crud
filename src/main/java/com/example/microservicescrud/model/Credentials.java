package com.example.microservicescrud.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CUSTOMERS")
public class Credentials {

    @Id
    @Column(name = "CUSTOMER_NUMBER")
    private String customerNumber;

    @Column(name = "PASSWORD")
    private String password;
}
