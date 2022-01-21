package com.example.microservicescrud.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDto {
    private String name;
    private String lastName;
    private String email;
}
